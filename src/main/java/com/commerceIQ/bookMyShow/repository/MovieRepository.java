package com.commerceIQ.bookMyShow.repository;

import com.commerceIQ.bookMyShow.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
