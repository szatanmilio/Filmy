package com.example.filmy.web;

import com.example.filmy.model.Production;
import com.example.filmy.model.User;
import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import com.example.filmy.service.MovieApi;
import com.example.filmy.service.ProductionServiceImpl;
import com.example.filmy.service.UserServiceImpl;
import com.example.filmy.web.dto.ProductionDto;
import com.example.filmy.web.dto.UserDto;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	private MovieApi api = new MovieApi();


	@GetMapping("/movie")
	public String getMovie(@RequestParam(name = "id", defaultValue = "120") int id,
						   @RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
						   Model model) {
		MovieDb movie = api.getMovie(id, lang);
		movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("Movie", movie);
		model.addAttribute("Production", new ProductionDto());
		return "production";
	}

	@GetMapping("/movieBest")
	public String getBestMovies(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
								@RequestParam(name = "page", defaultValue = "1") int page,
								Model model) {
		MovieResultsPage res = api.getBestMovies("en", page);
		for (MovieDb movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("bestMovies", res);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", res.getTotalPages());
		return "index";
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
		movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("Production", new ProductionDto());
		model.addAttribute("tvSeries", movie);
		return "production";
	}

	@GetMapping("/tvSeriesBest")
	public String getBestTvSeries(@RequestParam(name = "lang", defaultValue = "en", required = false) String lang,
								  @RequestParam(name = "page", defaultValue = "1") int page,
								  Model model) {
		TvResultsPage res = api.getBestTvSeries(lang, page);
		for (TvSeries movie : res.getResults())
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
		model.addAttribute("bestTv", res);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", res.getTotalPages());
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
		return getBestMovies("en", 1, model);
	}

	@PostMapping("/saveStatus")
	public String productionSubmit(@ModelAttribute ProductionDto production, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		Production productionToSave = productionRepository.findProductionByIdProductionAndUserByIdUserAndTypeEquals(production.getIdProduction(), currentUser, production.getType());
		if(productionToSave == null){
			productionToSave = new Production();
			productionToSave.setIdProduction(production.getIdProduction());
			productionToSave.setUserByIdUser(currentUser);
			productionToSave.setType(production.getType());
			productionToSave.setStatus(production.getStatus());
			productionRepository.save(productionToSave);
		} else{
			productionToSave.setStatus(production.getStatus());
			productionRepository.save(productionToSave);
		}
		model.addAttribute("Production", production);
		Long id = production.getIdProduction();
		if(production.getType().equals("MOVIE"))
			return "redirect:/movie?id="+id;
		else
			return "redirect:/tvSeries?id="+id;
	}

	@PostMapping("/changeUserPassword")
	public String userSubmit(@ModelAttribute UserDto user, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		if(passwordEncoder.matches(user.getaPassword(), currentUser.getPassword())){
			currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(currentUser);
			// TO DO :  dodac do modelu komunikat o sukcesie
			return "redirect:/settings";

		}
		else{
			// TO DO :  dodac do modelu komunikat o porazce
		}
//		model.addAttribute("Production", production);

		return "redirect:/settings";
	}

	@PostMapping("/changeUserEmail")
	public String emailSubmit(@ModelAttribute UserDto user, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		if(passwordEncoder.matches(user.getaPassword(), currentUser.getPassword())){
			currentUser.setEmail(user.getEmail());
			userRepository.save(currentUser);
			// TO DO :  dodac do modelu komunikat o sukcesie
			return "redirect:/settings";

		}
		else{
			// TO DO :  dodac do modelu komunikat o porazce
		}
//		model.addAttribute("Production", production);

		return "redirect:/settings";
	}
}
