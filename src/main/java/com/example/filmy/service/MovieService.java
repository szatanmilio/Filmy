package com.example.filmy.service;

import com.example.filmy.model.Movie;
import com.example.filmy.web.dto.MovieSaveDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MovieService {
	Movie save(MovieSaveDto movieToSave);
}
