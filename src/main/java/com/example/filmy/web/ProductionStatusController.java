package com.example.filmy.web;

import com.example.filmy.model.Production;
import com.example.filmy.model.User;
import com.example.filmy.repository.ProductionRepository;
import com.example.filmy.repository.UserRepository;
import com.example.filmy.web.dto.ProductionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	@Autowired
	ApiController apiController;
	@Autowired
	MainController mainController;


	@PostMapping("/saveStatus")
	public String productionSubmit(@ModelAttribute ProductionDto production, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		Production productionToSave = productionRepository.findProductionByIdProductionAndUserByIdUserAndTypeEquals(production.getIdProduction(), currentUser, production.getType());
		if (productionToSave == null) {
			productionToSave = new Production();
			productionToSave.setIdProduction(production.getIdProduction());
			productionToSave.setUserByIdUser(currentUser);
			productionToSave.setType(production.getType());
			productionToSave.setStatus(production.getStatus());
			productionRepository.save(productionToSave);
		} else {
			productionToSave.setStatus(production.getStatus());
			productionRepository.save(productionToSave);
		}
		model.addAttribute("Production", production);
		model.addAttribute("status", "Zmieniono status na: " + production.getStatus());
		Long id = production.getIdProduction();
		if (production.getType().equals("MOVIE"))
			return apiController.getMovie(Math.toIntExact(id), "en", model);
//			return "redirect:/movie?id="+id;
		else
			return apiController.getTvSeries(Math.toIntExact(id), "en", model);
//			return "redirect:/tvSeries?id="+id;
	}

	@PostMapping("/saveStatusList")
	public String productionSubmitList(@ModelAttribute ProductionDto production, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		Production productionToSave = productionRepository.findProductionByIdProductionAndUserByIdUserAndTypeEquals(production.getIdProduction(), currentUser, production.getType());
		if (productionToSave == null) {
			productionToSave = new Production();
			productionToSave.setIdProduction(production.getIdProduction());
			productionToSave.setUserByIdUser(currentUser);
			productionToSave.setType(production.getType());
			productionToSave.setStatus(production.getStatus());
			productionRepository.save(productionToSave);
		} else {
			productionToSave.setStatus(production.getStatus());
			productionRepository.save(productionToSave);
		}
		Long id = production.getIdProduction();
		return mainController.myList(model, production.getType());
	}

	public String getStatus(int id, String type) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		Production prod = productionRepository.findProductionByIdProductionAndUserByIdUserAndTypeEquals((long) id, currentUser, type);
		if (prod != null)
			return prod.getStatus();
		return "";
	}

	@PostMapping("/deleteFromList")
	public String deleteSubmitList(@ModelAttribute ProductionDto production, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userRepository.findByEmail(userDetails.getUsername());
		Production productionToDelete = productionRepository.findProductionByIdProductionAndUserByIdUserAndTypeEquals(production.getIdProduction(), currentUser, production.getType());
		String type = production.getType();
		productionRepository.delete(productionToDelete);
		return mainController.myList(model, type);
	}
}
