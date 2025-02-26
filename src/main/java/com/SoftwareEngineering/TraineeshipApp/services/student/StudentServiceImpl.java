package com.SoftwareEngineering.TraineeshipApp.services.student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import io.micrometer.observation.annotation.Observed;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentMapper studentMapper;


    private TraineeshipPositionsMapper positionsMapper;

    @Override
    public Student retrieveProfile(String studentUsername){

        return studentMapper.findByUsername(studentUsername);
        
    }

    @Override
    public void saveProfile(Student student){
        
        studentMapper.save(student);
    }

    @Override
    public void saveLogbook(TraineeshipPosition position){
        
    }
}

    