//package com.example.filmy.service;
//
//import com.example.filmy.model.Production;
//import com.example.filmy.model.User;
//import com.example.filmy.repository.ProductionRepository;
//import com.example.filmy.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductionServiceImpl {
//
//	private UserRepository userRepository;
//	private ProductionRepository productionRepository;
//
//	public ProductionServiceImpl(UserRepository userRepository, ProductionRepository productionRepository) {
//		this.userRepository = userRepository;
//		this.productionRepository = productionRepository;
//	}
//
//	public Production checkAndGetIfInDatabase(Production production, User user) {
//		return productionRepository.findProductionByIdProductionAndUserByIdUserAndTypeEquals(production.getIdProduction(), user, production.getType());
//	}
//
//	public void saveProduction(Production production){
//		productionRepository.save(production);
//	}
//
//
//	public User getCurrentUser(){
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return userRepository.findByEmail(userDetails.getUsername());
//	}
//}
