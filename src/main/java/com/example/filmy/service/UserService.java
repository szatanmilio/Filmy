package com.example.filmy.service;

import com.example.filmy.model.User;
import com.example.filmy.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
