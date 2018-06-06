package com.stackroute.moviecruiser.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Movie {
	@Id
	private String id;
	private String name;
	private String comments;
	private String posterPath;
	private String releaseDate;
	private Double voteAverage;
	private Integer voteCount;

    public Movie(String name, String comments, String posterPath, String releaseDate, Double voteAverage, Integer voteCount) {
        this.name = name;
        this.comments = comments;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }
}
