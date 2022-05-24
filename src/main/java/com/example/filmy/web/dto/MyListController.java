package com.example.filmy.web.dto;

import com.example.filmy.model.User;
import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
public class MyListController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;

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


}
