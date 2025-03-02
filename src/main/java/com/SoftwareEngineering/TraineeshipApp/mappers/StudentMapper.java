package com.SoftwareEngineering.TraineeshipApp.mappers;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;


import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentMapper extends JpaRepository<Student,Integer> {

    Student findByUsername(String username);
    
    List<Student> findByLookingForTraineeshipTrue();

    Student findById(int studentId);
    
}
