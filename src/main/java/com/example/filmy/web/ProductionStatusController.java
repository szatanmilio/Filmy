package com.example.filmy.web;

import com.example.filmy.model.Production;
import com.example.filmy.model.User;
import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import com.example.filmy.web.dto.ProductionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductionStatusController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;


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
}
