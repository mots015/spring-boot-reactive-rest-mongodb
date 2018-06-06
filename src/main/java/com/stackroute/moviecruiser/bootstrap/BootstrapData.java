package com.stackroute.moviecruiser.bootstrap;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BootstrapData implements CommandLineRunner {
    public BootstrapData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private MovieRepository movieRepository;

    private String name;
    private String comments;
    private String posterPath;
    private String releaseDate;
    private Double voteAverage;
    private Integer voteCount;
    @Override
    public void run(String... args) throws Exception {

        movieRepository
                .save(Movie.builder()
                        .name("Return of the Flux")
                        .comments("Sci-fi")
                        .posterPath("return_of_the_flux.png")
                        .releaseDate("01/01/1975")
                        .voteAverage(3.5)
                        .voteCount(156).build())
                .then()
                .block();//ensure processing completes before the next command is executed.

       //Saving stream of elements emitted by Publisher (Flux)
        movieRepository
                //Create a Flux that emit the provided elements and then completes.
                .saveAll(Flux.just(new Movie("Spiderman", "Kids", "spiderman.png", "01/01/2018", 4.4, 25000),
                        new Movie("Superman", "Kids", "superman.png", "01/01/2018", 4.4, 25000)))
                .subscribe(System.out::print);


    }
}
