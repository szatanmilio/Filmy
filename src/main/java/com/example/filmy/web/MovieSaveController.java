package com.example.filmy.web;

import com.example.filmy.service.MovieService;
import com.example.filmy.service.UserService;
import com.example.filmy.web.dto.MovieSaveDto;
import com.example.filmy.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/production")
public class MovieSaveController {

	private MovieService movieService;

	public MovieSaveController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@ModelAttribute("Movie")
	public MovieSaveDto movieSaveDto() {
		return new MovieSaveDto();
	}


	@PostMapping
	public String saveMovie(@ModelAttribute("Movie") MovieSaveDto movieToSave) {
		movieService.save(movieToSave);
		return "index";
//		return "redirect:/registration?success";
	}
}
