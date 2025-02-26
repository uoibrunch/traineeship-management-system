package com.SoftwareEngineering.TraineeshipApp.mappers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;


@Repository

public interface UserMapper extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	
}

