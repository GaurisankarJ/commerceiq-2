package com.commerceIQ.bookMyShow.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="movieid")
    Integer movieId;

    @Column(name="theaterid")
    Integer theaterId;

    @Column(name="moviename")
    String movieName;

    @Column(name="city")
    String city;

    @Column(name="morningshow")
    String morningShow;

    @Column(name="noonshow")
    String noonShow;

    @Column(name="nightshow")
    String nightShow;

    @Version
    private long version;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMorningShow() {
        return morningShow;
    }

    public void setMorningShow(String morningShow) {
        this.morningShow = morningShow;
    }

    public String getNoonShow() {
        return noonShow;
    }

    public void setNoonShow(String noonShow) {
        this.noonShow = noonShow;
    }

    public String getNightShow() {
        return nightShow;
    }

    public void setNightShow(String nightShow) {
        this.nightShow = nightShow;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", theaterId=" + theaterId +
                ", movieName='" + movieName + '\'' +
                ", city='" + city + '\'' +
                ", morningShow='" + morningShow + '\'' +
                ", noonShow='" + noonShow + '\'' +
                ", nightShow='" + nightShow + '\'' +
                '}';
    }
}
