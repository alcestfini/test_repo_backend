package com.digimaster.mybackend;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieModel,Integer>{
	MovieModel getMovieModelByTitleAndGenre(String title, String genre);
	MovieModel getMovieModelByGenreAndReleaseYear(String genre, String release_year);
	Iterable<MovieModel> getMovieModelByGenre(String genre);
}
