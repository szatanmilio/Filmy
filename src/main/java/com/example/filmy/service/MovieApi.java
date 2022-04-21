package com.example.filmy.service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.stereotype.Service;

@Service
public class MovieApi {
	private TmdbMovies movies = new TmdbApi("095f50ca603d72b2dec062fd8e73567c").getMovies();
	private TmdbTV tvSeries = new TmdbApi("095f50ca603d72b2dec062fd8e73567c").getTvSeries();

	public MovieDb getMovie(int id, String lang) {
		return movies.getMovie(id, lang);
	}

	public MovieResultsPage getBestMovies(String lang, int page) {
		return movies.getTopRatedMovies(lang, page);
	}

	public MovieResultsPage getPopularMovies(String lang, int page) {
		return movies.getPopularMovies(lang, page);
	}

	public MovieResultsPage getSimilarMovies(int id, String lang, int page) {
		return movies.getSimilarMovies(id, lang, page);
	}

	public MovieResultsPage getRecomendedMovies(int id, String lang, int page) {
		return movies.getRecommendedMovies(id, lang, page);
	}

	public TvSeries getTvSeries(int id, String lang) {
		return tvSeries.getSeries(id, lang);
	}

	public TvResultsPage getBestTvSeries(String lang, int page) {
		return tvSeries.getTopRated(lang, page);
	}

	public TvResultsPage getPopularTvSeries(String lang, int page) {
		return tvSeries.getPopular(lang, page);
	}
}
