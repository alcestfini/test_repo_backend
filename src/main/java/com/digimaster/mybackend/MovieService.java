package com.digimaster.mybackend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	private final Path root = Paths.get("/Users/seraphinatatiana/Documents/mybackend/images");
	public void saveFile(MultipartFile file){
		try {
			if(!Files.exists(root)) {

				Files.createDirectories(root);
			} 
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveFile(MultipartFile file, int id) {
		try {
			if(!Files.exists(root)) {

				Files.createDirectories(root);
			} 
			Optional<MovieModel> currentMovie = movieRepository.findById(id);
			if(currentMovie.isPresent()) {
				currentMovie.get().setImages(file.getOriginalFilename());
				movieRepository.save(currentMovie.get());
				Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public MovieModel createMovie(MovieModel movieModel) {
		return movieRepository.save(movieModel);
	}

	public Iterable<MovieModel> createMovie(Iterable<MovieModel> movie){
		return movieRepository.saveAll(movie);
	}
	public MovieModel getMovieModel(String title, String genre) {
		return movieRepository.getMovieModelByTitleAndGenre(title, genre);
	}
	public MovieModel getMovie(int id) {
		if(movieRepository.findById(id).isPresent()) {
			return movieRepository.findById(id).get();
		} 
		else {
			return null;
		}
			}
	
	
	public MovieModel getMovieModelByGenreAndReleaseYear(String genre, String release_year) {
		return movieRepository.getMovieModelByGenreAndReleaseYear(genre, release_year);
	}
	public Iterable<MovieModel> getAllMovies(){
		return movieRepository.findAll();
	}
	public Iterable<MovieModel> getMovieModelByGenre(String genre){
		return movieRepository.getMovieModelByGenre(genre);
	}
	public MovieModel updateMovie(MovieModel movieModel, int id){
		Optional<MovieModel> currentMovie = movieRepository.findById(id);
		if(currentMovie.isPresent()) {
			currentMovie.get().setTitle(movieModel.getTitle());
			currentMovie.get().setGenre(movieModel.getGenre());
			currentMovie.get().setReleaseYear(movieModel.getReleaseYear());
			
			return movieRepository.save(currentMovie.get());
		}
		else {
			return movieModel;
		}
	}
	public boolean deleteMovie(int id) {
		movieRepository.deleteById(id);
		return true;
	}
	
}
