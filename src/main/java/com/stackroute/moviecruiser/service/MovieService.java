package com.stackroute.moviecruiser.service;

import java.util.List;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.domain.MovieEvent;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface MovieService {

	Mono<Movie> saveMovie(Movie movie);
	Mono<Movie> getById(String movieId);
	Mono<Void> deleteMovieById(String id);
	Flux<Movie> getAllMovies();
	Flux<MovieEvent> events(String movieId);

}
