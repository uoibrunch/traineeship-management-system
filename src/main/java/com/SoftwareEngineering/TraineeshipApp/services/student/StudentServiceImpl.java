package com.SoftwareEngineering.TraineeshipApp.services.student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Logbook;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.LogbookMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



import io.micrometer.observation.annotation.Observed;
import lombok.extern.java.Log;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;

    @Autowired
    private LogbookMapper logbookMapper;

    @Override
    public Student retrieveProfile(String studentUsername){

        return studentMapper.findByUsername(studentUsername);
        
    }
    @Override
    public void saveProfile(Student student) {
        
        Student existingStudent = studentMapper.findByUsername(student.getUsername());
    
        if (existingStudent != null) {
            student.setStudentId(existingStudent.getStudentId());
            
            if (student.isLookingForTraineeship() && existingStudent.getAssignedTraineeship() != null) {
                TraineeshipPosition traineeship = existingStudent.getAssignedTraineeship();
    
                traineeship.setStudent(null);
                traineeship.setIsAssigned(false);
                positionsMapper.save(traineeship); 

                if (traineeship.getStudentLogbook() != null) {
                    traineeship.getStudentLogbook().clear();  
                }
    
                student.setAssignedTraineeship(null);
            } else {
                student.setAssignedTraineeship(existingStudent.getAssignedTraineeship());
            }
        }
    
        studentMapper.save(student);
    }
    

    @Override
    public void saveLogbook(Logbook logbook, Student student){

        TraineeshipPosition traineeshipPosition = student.getAssignedTraineeship();

        if (traineeshipPosition != null) {
          
            logbook.setTraineeshipPosition(traineeshipPosition);  

            logbookMapper.save(logbook);

            traineeshipPosition.getStudentLogbook().add(logbook);

            positionsMapper.save(traineeshipPosition);

        } else {

            throw new IllegalStateException("Student does not have an assigned traineeship position.");

        }

    }


}

    