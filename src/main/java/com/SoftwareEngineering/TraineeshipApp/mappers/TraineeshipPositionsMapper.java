package com.SoftwareEngineering.TraineeshipApp.mappers;


import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
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
    

}