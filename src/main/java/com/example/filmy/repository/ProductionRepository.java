package com.example.filmy.repository;

import com.example.filmy.model.Production;
import com.example.filmy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
	Production findProductionByIdProductionAndUserByIdUserAndTypeEquals(@Param("pId") Long pId, @Param("user") User user, @Param("type") String type);

	int countAllByUserByIdUserAndTypeEquals(@Param("user") User user, @Param("type") String type);

	int countAllByUserByIdUser(@Param("user") User user);

	int countAllByUserByIdUserAndTypeEqualsAndStatusEquals(@Param("user") User user, @Param("type") String type, @Param("status") String status);

	int countAllByUserByIdUserAndStatusEquals(@Param("user") User user, @Param("status") String status);

	List<Production> findAllByUserByIdUserAndTypeEquals(@Param("user") User user, @Param("type") String type);

	String findStatusByIdProductionAndUserByIdUserAndTypeEquals(@Param("pId") Long pId, @Param("user") User user, @Param("type") String type);
}
