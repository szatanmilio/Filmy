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
	@Autowired
	MainController mainController;


	@PostMapping("/changeUserPassword")
	public String userSubmit(@ModelAttribute UserDto user, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		if(passwordEncoder.matches(user.getaPassword(), currentUser.getPassword())){
			currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(currentUser);
			model.addAttribute("succesPassword", "Zmiana hasła zakończona pomyślnie");
			return mainController.settings(model);
		}
		else{
			model.addAttribute("errorPassword", "Zmiana hasła zakończona niepowodzeniem");
			return mainController.settings(model);
		}
	}

	@PostMapping("/changeUserEmail")
	public String emailSubmit(@ModelAttribute UserDto user, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		if(passwordEncoder.matches(user.getaPassword(), currentUser.getPassword())){
			currentUser.setEmail(user.getEmail());
			userRepository.save(currentUser);
			model.addAttribute("succesMail", "Zmiana adresu e-mail zakończona pomyślnie");
			return mainController.settings(model);
		}
		else{
			model.addAttribute("errorMail", "Zmiana adresu e-mail zakończona niepowodzeniem");
			return mainController.settings(model);
		}
	}


	@PostMapping("/deleteAccount")
	public String deleteAccount(@ModelAttribute UserDto user, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		if(passwordEncoder.matches(user.getaPassword(), currentUser.getPassword())){
			userRepository.delete(currentUser);
			return "redirect:/logout";
//			userRepository.save(currentUser);
//			model.addAttribute("succesMail", "Zmiana adresu e-mail zakończona pomyślnie");
//			return mainController.settings(model);
		}
		else{
			model.addAttribute("errorDelete", "Wprowadzono błędne hasło");
			return mainController.settings(model);
		}
	}
}
