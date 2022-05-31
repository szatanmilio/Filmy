package com.example.filmy.web;

import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import com.example.filmy.service.MovieApi;
import com.example.filmy.web.dto.ProductionDto;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApiController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;
	@Autowired
	ProductionStatusController productionStatusController;
	public MovieApi api = new MovieApi();
	private String lastCategoryPage;
	private String lastSearchQuery;


	@GetMapping("/movie")
	public String getMovie(@RequestParam(name = "id", defaultValue = "120") int id,
						   @RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
						   Model model) {
		MovieDb movie = api.getMovie(id, lang);
		movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		String status = productionStatusController.getStatus(id, "MOVIE");
		model.addAttribute("Movie", movie);
		model.addAttribute("Production", new ProductionDto());
		model.addAttribute("currentStatus", status);
		return "production";
	}

	@GetMapping("/movieBest")
	public String getBestMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
								@RequestParam(name = "page", defaultValue = "1") int page,
								Model model) {
		MovieResultsPage res = api.getBestMovies("en", page);
		for (MovieDb movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("Movies", res);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", res.getTotalPages());
		model.addAttribute("pageCategory", "MovieHighRate");
		lastCategoryPage = "MovieHighRate";
		return "index";
	}

	@GetMapping("/moviePopular")
	public String getPopularMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
								   @RequestParam(name = "page", defaultValue = "1") int page,
								   Model model) {
		MovieResultsPage res = api.getPopularMovies("en", page);
		for (MovieDb movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("Movies", res);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", res.getTotalPages());
		model.addAttribute("pageCategory", "MoviePopularityDesc");
		lastCategoryPage = "MoviePopularityDesc";
		return "index";
	}

	@GetMapping("/tvSeries")
	public String getTvSeries(@RequestParam(name = "id", defaultValue = "123") int id,
							  @RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
							  Model model) {
		TvSeries movie = api.getTvSeries(id, lang);
		movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		String status = productionStatusController.getStatus(id, "TV");
		model.addAttribute("Production", new ProductionDto());
		model.addAttribute("tvSeries", movie);
		model.addAttribute("currentStatus", status);
		return "production";
	}

	@GetMapping("/tvSeriesBest")
	public String getBestTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
								  @RequestParam(name = "page", defaultValue = "1") int page,
								  Model model) {
		TvResultsPage res = api.getBestTvSeries(lang, page);
		for (TvSeries movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("Tv", res);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", res.getTotalPages());
		model.addAttribute("pageCategory", "TvHighRate");
		lastCategoryPage = "TvHighRate";
		return "index";
	}

	@GetMapping("/tvSeriesPopular")
	public String getPopularTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
									 @RequestParam(name = "page", defaultValue = "1") int page,
									 Model model) {
		TvResultsPage res = api.getPopularTvSeries(lang, page);
		for (TvSeries movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("Tv", res);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", res.getTotalPages());
		model.addAttribute("pageCategory", "TvPopularityDesc");
		lastCategoryPage = "TvPopularityDesc";
		return "index";
	}

	@GetMapping("/searchResults")
	public String getSearch(@RequestParam(name = "query", defaultValue = "") String query,
							@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
							@RequestParam(name = "adult", defaultValue = "false", required = false) boolean adult,
							@RequestParam(name = "page", defaultValue = "1", required = false) int page,
							@RequestParam(name = "type", defaultValue = "MOVIE") String type,
							Model model) {
		if (type.equals("MOVIE")) {
			MovieResultsPage res = api.searchForProduction(query, lang, adult, page);
			for (MovieDb movie : res.getResults())
				movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
			model.addAttribute("Movies", res);
			model.addAttribute("page", page);
			model.addAttribute("totalPages", res.getTotalPages());
			model.addAttribute("pageCategory", "MovieSearch");
			lastCategoryPage = "MovieSearch";
			lastSearchQuery = query;
			return "index";
		} else {
			TvResultsPage res = api.searchForProductionTv(query, lang, page);
			for (TvSeries movie : res.getResults())
				movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
			model.addAttribute("Tv", res);
			model.addAttribute("page", page);
			model.addAttribute("totalPages", res.getTotalPages());
			model.addAttribute("pageCategory", "TvSearch");
			lastCategoryPage = "TvSearch";
			lastSearchQuery = query;
			return "index";
		}
	}

	@GetMapping("/")
	public String bestMovies(Model model) {
		return getBestMovies("en", 1, model);
	}


	@PostMapping("/filter")
	public String filter(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
						 @RequestParam(name = "page", defaultValue = "1") int page,
						 @RequestParam(name = "sort", defaultValue = "highRate") String sort,
						 @RequestParam(name = "type", defaultValue = "MOVIE") String type,
						 Model model) {
		if (sort.equals("highRate")) {
			return type.equals("MOVIE") ? getBestMovies(lang, page, model) : getBestTvSeries(lang, page, model);
		} else if (sort.equals("lowRate")) {
			return type.equals("MOVIE") ? getWorstMovies(lang, page, model) : getWorstTvSeries(lang, page, model);
		} else if (sort.equals("popularityDesc")) {
			return type.equals("MOVIE") ? getPopularMovies(lang, page, model) : getPopularTvSeries(lang, page, model);
		} else if (sort.equals("popularityAsc")) {
			//TO DO
		}
		return getBestMovies("en", 1, model);
	}

	private String getWorstTvSeries(String lang, int page, Model model) {
		//TO DO
		return "index";
	}

	private String getWorstMovies(String lang, int page, Model model) {
		//TO DO
		return "index";
	}

	@PostMapping("/nextPage")
	public String nextPage(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
						   @RequestParam(name = "page", defaultValue = "1") int page,
						   @RequestParam(name = "categoryPage", defaultValue = "BRAK") String categoryPage,
						   Model model) {
		if (!categoryPage.equals(lastCategoryPage) && !categoryPage.equals("BRAK"))
			page = 1;
		if (categoryPage.equals("BRAK"))
			categoryPage = lastCategoryPage;
		if (categoryPage.equals("MovieHighRate")) {
			return getBestMovies(lang, page, model);
		} else if (categoryPage.equals("MovieLowRate")) {

		} else if (categoryPage.equals("MoviePopularityDesc")) {
			return getPopularMovies(lang, page, model);
		} else if (categoryPage.equals("MoviePopularityAsc")) {

		} else if (categoryPage.equals("TvHighRate")) {
			return getBestTvSeries(lang, page, model);
		} else if (categoryPage.equals("TvLowRate")) {

		} else if (categoryPage.equals("TvPopularityDesc")) {
			return getPopularTvSeries(lang, page, model);
		} else if (categoryPage.equals("TvPopularityAsc")) {

		} else if (categoryPage.equals("MovieSearch")) {
			return getSearch(lastSearchQuery, "en", false, page, "MOVIE", model);
		} else if (categoryPage.equals("TvSearch")) {
			return getSearch(lastSearchQuery, "en", false, page, "TV", model);
		}
		return getBestMovies("en", 1, model);
	}
}
