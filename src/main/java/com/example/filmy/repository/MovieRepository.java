package com.example.filmy.repository;

import com.example.filmy.model.Movie;
import com.example.filmy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
//	Movie findById(String id);
//	Movie findByName(String username);
}
