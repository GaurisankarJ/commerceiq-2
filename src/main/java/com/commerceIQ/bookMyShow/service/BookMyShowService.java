package com.commerceIQ.bookMyShow.service;

import com.commerceIQ.bookMyShow.model.Booking;
import com.commerceIQ.bookMyShow.model.Movie;
import com.commerceIQ.bookMyShow.model.Theater;
import com.commerceIQ.bookMyShow.repository.BookingRepository;
import com.commerceIQ.bookMyShow.repository.MovieRepository;
import com.commerceIQ.bookMyShow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookMyShowService {

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @PersistenceContext
    public EntityManager entityManager;

    public Theater addTheater(Theater newTheater) {
        return theaterRepository.save(newTheater);
    }

    public List<Theater> listAllTheaters() {
        return (List<Theater>) theaterRepository.findAll();
    }

    public Movie addMovie(Movie newMovie) {
        Optional<Theater> theater = theaterRepository.findById(newMovie.getTheaterId());
        newMovie.setCity(theater.get().getCity());
        newMovie.setMorningShow("1,1,1,1,1");
        newMovie.setNoonShow("1,1,1,1,1");
        newMovie.setNightShow("1,1,1,1,1");

        return movieRepository.save(newMovie);
    }

    public List<String> listAllCities() {
        List<Theater> listOfCities = (List<Theater>) theaterRepository.findAll();
        List<String> allCities = listOfCities.stream().map(object -> object.getCity()).collect(Collectors.toList());
        Set<String> distinctCities = new TreeSet<>(allCities);

        return new ArrayList<>(distinctCities);
    }

    public List<String> listMoviesInCity(String city) {
        List<Movie> listOfMovies = (List<Movie>) movieRepository.findAll();
        List<String> moviesInCity = listOfMovies.stream()
                .filter(object -> object.getCity().equals(city))
                .map((object -> object.getMovieName()))
                .collect(Collectors.toList());

        return moviesInCity;
    }

    public List<Theater> listTheatersInCityWithMovie(String city, String movie) {
        List<Movie> listOfMovies = (List<Movie>) movieRepository.findAll();
        List<Movie> movieInCity = listOfMovies.stream()
                .filter(object -> object.getCity().equals(city) && object.getMovieName().equals(movie))
                .collect(Collectors.toList());
        List<Theater> theaters = new ArrayList<>();
        for (Movie object : movieInCity) {
            Optional<Theater> byId = theaterRepository.findById(object.getTheaterId());
            theaters.add(byId.get());
        }

        return theaters;
    }

    public Movie listShowsInTheatersInCityWithMovie(String city, String movie, Integer theaterId) {
        List<Movie> listOfMovies = (List<Movie>) movieRepository.findAll();
        List<Movie> shows = listOfMovies.stream()
                .filter(object -> object.getCity().equals(city) && object.getMovieName().equals(movie) && object.getTheaterId() == theaterId)
                .collect(Collectors.toList());

        return shows.get(0);
    }

    @Transactional
    public ResponseEntity<Booking> bookMovie(Booking newBooking) {
        Movie movie = entityManager.find(Movie.class, newBooking.getMovieId());
        entityManager.lock(movie, LockModeType.OPTIMISTIC);

        String[] allSeats = new String[5];

        switch (newBooking.getShow()) {
            case "morning":
                allSeats = movie.getMorningShow().split(",");
                break;
            case "noon":
                allSeats = movie.getNoonShow().split(",");
                break;
            case "night":
                allSeats = movie.getNightShow().split(",");
                break;
        }

        String[] seats = newBooking.getSeats().split(",");
        try {
            for(String i: seats) {
                if(allSeats[Integer.parseInt(i)].equals("1")) {
                    allSeats[Integer.parseInt(i)] = "0";
                } else {
                    entityManager.lock(movie, LockModeType.NONE);

                    throw new Exception();
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        switch (newBooking.getShow()) {
            case "morning":
                movie.setMorningShow(String.join(",", allSeats));
                break;
            case "noon":
                movie.setNoonShow(String.join(",", allSeats));
                break;
            case "night":
                movie.setNightShow(String.join(",", allSeats));
                break;
        }

        Booking booking = new Booking();
        booking.setMovieId(newBooking.getMovieId());
        booking.setTheaterId(newBooking.getTheaterId());
        booking.setName(newBooking.getName());
        booking.setPhoneNumber(newBooking.getPhoneNumber());
        booking.setSeats(String.join(",", seats));
        booking.setShow(newBooking.getShow());

        entityManager.lock(movie, LockModeType.NONE);

        return ResponseEntity.ok(bookingRepository.save(booking));
    }
}
