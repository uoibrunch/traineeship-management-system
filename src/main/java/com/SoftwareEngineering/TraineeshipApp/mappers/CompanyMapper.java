package com.SoftwareEngineering.TraineeshipApp.mappers;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company; 
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompanyMapper extends JpaRepository<Company, Integer>{
	
}