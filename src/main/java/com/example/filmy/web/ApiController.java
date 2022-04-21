package com.example.filmy.web;

import com.example.filmy.service.MovieApi;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	private MovieApi api = new MovieApi();

	@GetMapping("/movie")
	@ResponseBody
	public MovieDb getMovie(@RequestParam(name = "id", defaultValue = "123") int id,
							@RequestParam(name = "lang", defaultValue = "en", required = false) String lang){
		return api.getMovie(id, lang);
	}

	@GetMapping("/movieBest")
	@ResponseBody
	public MovieResultsPage getBestMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
												@RequestParam(name = "page", defaultValue = "1") int page){
		return api.getBestMovies(lang, page);
	}

	@GetMapping("/moviePopular")
	@ResponseBody
	public MovieResultsPage getPopularMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
										  @RequestParam(name = "page", defaultValue = "1") int page){
		return api.getPopularMovies(lang, page);
	}

	@GetMapping("/movieSimilar")
	@ResponseBody
	public MovieResultsPage getSimilarMovies( @RequestParam(name = "id", defaultValue = "123") int id,
											  @RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
											 @RequestParam(name = "page", defaultValue = "1") int page){
		return api.getSimilarMovies(id, lang, page);
	}

	@GetMapping("/movieRecomended")
	@ResponseBody
	public MovieResultsPage getRecomendedMovies( @RequestParam(name = "id", defaultValue = "123") int id,
											  @RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
											  @RequestParam(name = "page", defaultValue = "1") int page){
		return api.getRecomendedMovies(id, lang, page);
	}

	@GetMapping("/tvSeries")
	@ResponseBody
	public TvSeries getTvSeries(@RequestParam(name = "id", defaultValue = "123") int id,
								@RequestParam(name = "lang", defaultValue = "en", required = false) String lang){
		return api.getTvSeries(id, lang);
	}

	@GetMapping("/tvSeriesBest")
	@ResponseBody
	public TvResultsPage getBestTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
										 @RequestParam(name = "page", defaultValue = "1") int page){
		return api.getBestTvSeries(lang, page);
	}

	@GetMapping("/tvSeriesPopular")
	@ResponseBody
	public TvResultsPage getPopularTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
											 @RequestParam(name = "page", defaultValue = "1") int page){
		return api.getPopularTvSeries(lang, page);
	}

	@GetMapping("/")
	public String bestMovies(Model model){
		MovieResultsPage res = getBestMovies("en", 1);
		model.addAttribute("bestMovies", res);
		return "index";
	}

}
