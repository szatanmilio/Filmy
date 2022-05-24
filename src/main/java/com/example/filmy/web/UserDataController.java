package com.example.filmy.web;

import com.example.filmy.model.User;
import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import com.example.filmy.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserDataController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;


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
