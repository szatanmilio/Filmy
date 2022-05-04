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
	public String getMovie(@RequestParam(name = "id", defaultValue = "120") int id,
							@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
							Model model) {
		MovieDb movie = api.getMovie(id, lang);
		movie.setPosterPath("https://image.tmdb.org/t/p/original"+movie.getPosterPath());
		model.addAttribute("Movie",movie);
		return "movie";
	}

	@GetMapping("/movieBest")
	@ResponseBody
	public MovieResultsPage getBestMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
										  @RequestParam(name = "page", defaultValue = "1") int page) {
		return api.getBestMovies(lang, page);
	}

	@GetMapping("/moviePopular")
	@ResponseBody
	public MovieResultsPage getPopularMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
											 @RequestParam(name = "page", defaultValue = "1") int page) {
		return api.getPopularMovies(lang, page);
	}

	@GetMapping("/movieSimilar")
	@ResponseBody
	public MovieResultsPage getSimilarMovies(@RequestParam(name = "id", defaultValue = "123") int id,
											 @RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
											 @RequestParam(name = "page", defaultValue = "1") int page) {
		return api.getSimilarMovies(id, lang, page);
	}

	@GetMapping("/movieRecomended")
	@ResponseBody
	public MovieResultsPage getRecomendedMovies(@RequestParam(name = "id", defaultValue = "123") int id,
												@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
												@RequestParam(name = "page", defaultValue = "1") int page) {
		return api.getRecomendedMovies(id, lang, page);
	}

	@GetMapping("/tvSeries")
	public String getTvSeries(@RequestParam(name = "id", defaultValue = "123") int id,
								@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
							  Model model) {
		TvSeries movie = api.getTvSeries(id, lang);
		movie.setPosterPath("https://image.tmdb.org/t/p/original"+movie.getPosterPath());
		model.addAttribute("tvSeries",movie);
		return "tvSeries";
	}

	@GetMapping("/tvSeriesBest")
//	@ResponseBody
	public String getBestTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
										 @RequestParam(name = "page", defaultValue = "1") int page,
										 Model model) {
		TvResultsPage res = api.getBestTvSeries(lang, page);
		for(TvSeries movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original"+movie.getPosterPath());
		model.addAttribute("bestTv",res);
		return "index";
	}

	@GetMapping("/tvSeriesPopular")
	@ResponseBody
	public TvResultsPage getPopularTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
											@RequestParam(name = "page", defaultValue = "1") int page) {
		return api.getPopularTvSeries(lang, page);
	}

	@GetMapping("/search")
	@ResponseBody
	public MovieResultsPage searchForProduction(@RequestParam(name = "query", defaultValue = "") String query,
												@RequestParam(name = "year", defaultValue = "null", required = false) int year,
												@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
												@RequestParam(name = "adult", defaultValue = "true", required = false) boolean adult,
												@RequestParam(name = "page", defaultValue = "1") int page) {
		return api.searchForProduction(query, year, lang, adult, page);
	}

	@GetMapping("/")
	public String bestMovies(Model model) {
		MovieResultsPage res = getBestMovies("en", 1);
		for(MovieDb movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original"+movie.getPosterPath());
		model.addAttribute("bestMovies", res);
		return "index";
	}

}
