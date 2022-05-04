package com.example.filmy.service;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.stereotype.Service;

@Service
public class MovieApi {
	String apiKey = "095f50ca603d72b2dec062fd8e73567c";
	TmdbSearch search = new TmdbSearch(new TmdbApi(apiKey));
//	MovieResultsPage testsearch = search.searchMovie("lord",null,"en",true,1);
	private TmdbMovies movies = new TmdbApi(apiKey).getMovies();
	private TmdbTV tvSeries = new TmdbApi(apiKey).getTvSeries();

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

	public MovieResultsPage searchForProduction(String query, int year, String lang, boolean adult, int page){
		return search.searchMovie("lord",null,"en",true,1);
	}
}
