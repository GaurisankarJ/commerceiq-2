package com.commerceIQ.bookMyShow.controller;

import com.commerceIQ.bookMyShow.model.Booking;
import com.commerceIQ.bookMyShow.model.Movie;
import com.commerceIQ.bookMyShow.model.Theater;
import com.commerceIQ.bookMyShow.service.BookMyShowService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookMyShowController {
    @Autowired
    private BookMyShowService bookMyShowService;

    @ApiOperation("Add Theater")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @PostMapping("/theaters/add")
    public Theater addTheater(
            @RequestBody Theater newTheater
    ) {
        return bookMyShowService.addTheater(newTheater);
    }

    @ApiOperation("Get All Theaters")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @GetMapping("/theaters")
    public List<Theater> getAllTheaters() {
        return bookMyShowService.listAllTheaters();
    }

    @ApiOperation("Add Movie")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @PostMapping("/movies/add")
    public Movie addMovie(
            @RequestBody Movie newMovie
    ) {
        return bookMyShowService.addMovie(newMovie);
    }

    @ApiOperation("Get Cities")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @GetMapping("/cities")
    public List<String> getAllCities() {
        return bookMyShowService.listAllCities();
    }

    @ApiOperation("Get Movies")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @GetMapping("/movies")
    public List<String> getAllMoviesInCity(
            @ApiParam(value = "City to search movies")
            @RequestParam(value = "city") String city
    ) {
        return bookMyShowService.listMoviesInCity(city);
    }

    @ApiOperation("Get Theaters With Movie")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @GetMapping("/theater/find")
    public List<Theater> getAllTheatersInCityWithMovie(
            @ApiParam(value = "City to search theater")
            @RequestParam(value = "city") String city,
            @ApiParam(value = "Movie to search theater")
            @RequestParam(value = "movie") String movie
    ) {
        return bookMyShowService.listTheatersInCityWithMovie(city, movie);
    }

    @ApiOperation("Get Shows In Theater With Movie")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @GetMapping("/shows")
    public Movie getAllShowsInTheatersInCityWithMovie(
            @ApiParam(value = "City to search show")
            @RequestParam(value = "city") String city,
            @ApiParam(value = "Movie to search show")
            @RequestParam(value = "movie") String movie,
            @ApiParam(value = "TheaterId to search show")
            @RequestParam(value = "theaterId") Integer theaterId
    ) {
        return bookMyShowService.listShowsInTheatersInCityWithMovie(city, movie, theaterId);
    }

    @ApiOperation("Book Movie")
    @ApiResponses({
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @PostMapping("/book")
    public ResponseEntity<Booking> postBookMovie(
            @RequestBody Booking newBooking
    ) {
        return bookMyShowService.bookMovie(newBooking);
    }
}
