package com.example.filmy.repository;

import com.example.filmy.model.Production;
import com.example.filmy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProductionRepository extends JpaRepository<Production, Long> {
	Production findProductionByIdProductionAndUserByIdUserAndTypeEquals(@Param("pId") Long pId, @Param("user") User user,@Param("type") String type);
}
