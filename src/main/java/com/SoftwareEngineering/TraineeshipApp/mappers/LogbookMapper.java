package com.SoftwareEngineering.TraineeshipApp.mappers;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Logbook;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LogbookMapper extends JpaRepository<Logbook, Integer>{

   

}