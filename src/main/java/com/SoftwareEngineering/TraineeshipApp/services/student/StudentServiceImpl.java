package com.SoftwareEngineering.TraineeshipApp.services.student;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;

    @Autowired
    private LogbookMapper logbookMapper;

    @Autowired
    private ProfessorMapper professorMapper;

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

                Professor supervisor = traineeship.getSupervisor();
                if (supervisor != null) {
                    supervisor.getSupervisedPositions().remove(traineeship);
                    traineeship.setSupervisor(null);
                    traineeship.setIsSupervised(false); 
                    professorMapper.save(supervisor);
                }

                if (traineeship.getStudentLogbook() != null) {
                    traineeship.getStudentLogbook().clear();  
                }

                student.setAssignedTraineeship(null);

                positionsMapper.save(traineeship); 
    
                
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

    @Override
    public void saveUsernameAndId(Student student){

        student.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) userDetails;
        
        student.setStudentId(user.getId());

    }

    @Override
    public String extractUsernameFromUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }


}

    