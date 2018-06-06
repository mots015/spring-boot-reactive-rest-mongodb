package com.stackroute.moviecruiser.controller;

import java.util.List;

import com.stackroute.moviecruiser.domain.MovieEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.service.MovieService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "/api/movie")
public class MovieController {

	private  MovieService movieService;

	@Autowired
	public MovieController(final MovieService movieService) {
		this.movieService = movieService;
	}

// Supported JSON
//  {
//            "name":"Superman3",
//            "comments": "Movie Sci Fi",
//            "posterPath":"www.movieposter/sc-fi/superman.png",
//            "releaseDate":"12.12/2017",
//            "voteAverage":"45",
//            "voteCount":2500
//    }
	@PostMapping
	public Mono<ResponseEntity<Movie>> saveNewMovie(@RequestBody final Movie movie) {
		return this.movieService.saveMovie(movie)
				.map(savedMovie -> new ResponseEntity<>(savedMovie, HttpStatus.CREATED));
	}


	@PutMapping(path = "{id}")
	public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable("id") final String id, @RequestBody Movie movie) {



        movie.setId(id);
		return this.movieService.saveMovie(movie)
				.map(updatedMovie -> new ResponseEntity<>(updatedMovie, HttpStatus.CREATED))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}


	@DeleteMapping(value = "/{id}")
	public Mono<ResponseEntity<Void>> deleteMovieById(@PathVariable("id") final String id) {

		return this.movieService.deleteMovieById(id)
                .map(deletedMovie -> new ResponseEntity<>(deletedMovie, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/{id}")
	public Mono<ResponseEntity<Movie>> fetchMovieById(@PathVariable("id") final String id) {

        return movieService.getById(id)
                .map(savedMovie -> ResponseEntity.ok(savedMovie))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Flux<Movie> fetchAllMovies() {
		return  movieService.getAllMovies();
	}

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id){
        return movieService.events(id);
    }
}
