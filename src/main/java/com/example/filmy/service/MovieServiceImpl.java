package com.example.filmy.service;

import com.example.filmy.model.Movie;
import com.example.filmy.repository.MovieRepository;
import com.example.filmy.web.dto.MovieSaveDto;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
	private MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie save(MovieSaveDto movieToSave) {
		Movie movie = new Movie(null, movieToSave.getRating(), movieToSave.getList());
		return movieRepository.save(movie);
	}
}
