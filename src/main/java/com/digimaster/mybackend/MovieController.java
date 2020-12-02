package com.digimaster.mybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/create")
	public MovieModel createMovie(@RequestBody MovieModel movieModel) {
		return movieService.createMovie(movieModel);
	}
	@PostMapping("/createmovies")
	public Iterable<MovieModel> createMovie(@RequestBody Iterable<MovieModel> movie) {
		return movieService.createMovie(movie);
	}
	
	@GetMapping("/title")
	public MovieModel getMovieModel(@RequestParam String title, @RequestParam String genre) {
	return movieService.getMovieModel(title, genre);
	}
	
	@GetMapping("/movie/genre")
	public MovieModel getMovieModelByGenreAndReleaseYear(@RequestParam String genre, @RequestParam String release_year) {
	return movieService.getMovieModelByGenreAndReleaseYear(genre, release_year);
	}
	@GetMapping("/movies")
	public MovieResponse<MovieModel> getMovieWithMovieResponse(@RequestParam int id) {
		MovieModel movie = movieService.getMovie(id);
		MovieResponse<MovieModel> movieResponse =  new MovieResponse<>();
		if(movie != null) {
			movieResponse.setCode(200);
			movieResponse.setSuccess(true);
			movieResponse.setMessage("user found");
			movieResponse.setData(movie);
			
		} else {
			movieResponse.setCode(404);
			movieResponse.setSuccess(false);
			movieResponse.setMessage("user not found");
			movieResponse.setData(movie);
		}
		return movieResponse;
	}
	@GetMapping("/allmovies")
	public Iterable<MovieModel> getAllMovies(){
		return movieService.getAllMovies();
	}
	@GetMapping("/moviegenre")
	public Iterable<MovieModel> getMovieModelByGenre(@RequestParam String genre){
		return movieService.getMovieModelByGenre(genre);
	}
	@PutMapping("/movie/{id}")
	public MovieModel updateMovie(@PathVariable int id, @RequestBody MovieModel movieModel) {
		return movieService.updateMovie(movieModel, id);
	}
	@DeleteMapping("/delete/{id}")
	public boolean deletePerson(@PathVariable int id) {
		return movieService.deleteMovie(id);
	}
	@PostMapping("/uploadimages")
	public boolean uploadFile(@RequestParam("file")MultipartFile file) {
		movieService.saveFile(file);
		return true;
	}
	@PostMapping("/files")
	public boolean uploadFile(@RequestParam("file")MultipartFile file, @RequestParam int id) {
		movieService.saveFile(file, id);
		return true;
	}
}
