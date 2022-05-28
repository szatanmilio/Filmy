package com.example.filmy.web;

import com.example.filmy.model.Production;
import com.example.filmy.model.User;
import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import com.example.filmy.web.ApiController;
import com.example.filmy.web.dto.MyListDto;
import com.example.filmy.web.dto.ProductionDto;
import com.example.filmy.web.dto.ProductionStatDto;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyListController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;
	@Autowired
	ApiController apiController;
	@Autowired
	MainController mainController;

	public ProductionStatDto getAllData(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		ProductionStatDto data = new ProductionStatDto();
		data.setMovieAll(productionRepository.countAllByUserByIdUserAndTypeEquals(currentUser,"MOVIE"));
		data.setMovieWatched(productionRepository.countAllByUserByIdUserAndTypeEqualsAndStatusEquals(currentUser,"MOVIE","OBEJRZANY"));
		data.setMovieWatching(productionRepository.countAllByUserByIdUserAndTypeEqualsAndStatusEquals(currentUser,"MOVIE","OGLADAM"));
		data.setMovieFuture(productionRepository.countAllByUserByIdUserAndTypeEqualsAndStatusEquals(currentUser,"MOVIE","PLANUJE"));
		data.setTvAll(productionRepository.countAllByUserByIdUserAndTypeEquals(currentUser,"TV"));
		data.setTvWatched(productionRepository.countAllByUserByIdUserAndTypeEqualsAndStatusEquals(currentUser,"TV","OBEJRZANY"));
		data.setTvWatching(productionRepository.countAllByUserByIdUserAndTypeEqualsAndStatusEquals(currentUser,"TV","OGLADAM"));
		data.setTvFuture(productionRepository.countAllByUserByIdUserAndTypeEqualsAndStatusEquals(currentUser,"TV","PLANUJE"));
		data.setAll(productionRepository.countAllByUserByIdUser(currentUser));
		data.setAllWatched(productionRepository.countAllByUserByIdUserAndStatusEquals(currentUser,"OBEJRZANY"));
		data.setAllWatching(productionRepository.countAllByUserByIdUserAndStatusEquals(currentUser,"OGLADAM"));
		data.setAllFuture(productionRepository.countAllByUserByIdUserAndStatusEquals(currentUser,"PLANUJE"));
		return data;
	}

	public List<MovieDb> getMyMovies(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		List<Production> listFromDatabase = new ArrayList<>();
		List<MovieDb> listFromApi = new ArrayList<>();
		listFromDatabase = productionRepository.findAllByUserByIdUserAndTypeEquals(currentUser, "MOVIE");
		for(Production p : listFromDatabase){
			MovieDb movie = apiController.api.getMovie((int) p.getIdProduction(), "en");
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
			listFromApi.add(movie);
		}
		return listFromApi;
	}

	@PostMapping("/myList")
	public String myList(@ModelAttribute MyListDto myListDto, Model model) {
		return mainController.myList(model, myListDto.getType());
	}

	public List<TvSeries> getMyTvSeries() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		List<Production> listFromDatabase = new ArrayList<>();
		List<TvSeries> listFromApi = new ArrayList<>();
		listFromDatabase = productionRepository.findAllByUserByIdUserAndTypeEquals(currentUser, "TV");
		for(Production p : listFromDatabase){
			TvSeries movie = apiController.api.getTvSeries((int) p.getIdProduction(), "en");
			movie.setPosterPath("https://image.tmdb.org/t/p/original" + movie.getPosterPath());
			listFromApi.add(movie);
		}
		return listFromApi;
	}
}
