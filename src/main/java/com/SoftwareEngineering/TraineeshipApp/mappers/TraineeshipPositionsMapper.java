package com.SoftwareEngineering.TraineeshipApp.mappers;


import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface TraineeshipPositionsMapper extends JpaRepository<TraineeshipPosition,Integer>{

    @Query("SELECT t FROM TraineeshipPosition t WHERE t.skills IN :requiredSkills")
    List<TraineeshipPosition> findByRequiredSkillsIn(@Param("requiredSkills") List<String> requiredSkills);
    
    List<TraineeshipPosition> findByIsAssignedFalse();

    List<TraineeshipPosition> findByIsAssignedTrue();

    @Query("SELECT t FROM TraineeshipPosition t WHERE t.topics LIKE %:interest%")
    List<TraineeshipPosition> findByTopicsContaining(@Param("interest") String interest);

    Optional<TraineeshipPosition> findById(Integer traineeshipId);

    List<TraineeshipPosition> findByCompanyAndIsAssignedFalse(Company company);

    List<TraineeshipPosition> findByCompanyAndIsAssignedTrue (Company company);

    TraineeshipPosition findById(int studentId);
    
    List<TraineeshipPosition> findByIsAssignedTrueAndIsSupervisedFalse();


	
}