package com.stackroute.moviecruiser.service;



import com.stackroute.moviecruiser.domain.MovieEvent;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.repository.MovieRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;
import java.util.Date;


@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepo;


	@Autowired
	public MovieServiceImpl(final MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}



	@Override
	public Mono<Movie> saveMovie(final Movie movie) {
		return movieRepo.save(movie);
	}


	@Override
	public Mono<Void> deleteMovieById(final String id)  {
//		movieRepo.deleteById(id).block();
//		return Mono.empty();
		return movieRepo.deleteById(id);
	}

	public Mono<Movie> getById(String movieId){
		return movieRepo.findById(movieId);
	}

	@Override
	public Flux<Movie> getAllMovies() {
		return movieRepo.findAll();
	}

	@Override
	public Flux<MovieEvent> events(String movieId) {
		return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
					movieEventSynchronousSink.next(new MovieEvent(movieId,new Date()));
				}
		).delayElements(Duration.ofSeconds(1));
	}

}
