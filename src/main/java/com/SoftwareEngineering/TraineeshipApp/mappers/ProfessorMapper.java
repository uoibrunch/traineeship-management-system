package com.SoftwareEngineering.TraineeshipApp.mappers;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProfessorMapper extends JpaRepository<Professor,Integer> {
    
    Professor findByUsername(String username);
    
}