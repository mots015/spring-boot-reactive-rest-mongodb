package com.stackroute.moviecruiser.repository;


import com.stackroute.moviecruiser.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
