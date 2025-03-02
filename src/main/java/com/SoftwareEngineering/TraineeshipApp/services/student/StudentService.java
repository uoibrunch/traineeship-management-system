package com.SoftwareEngineering.TraineeshipApp.services.student;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Logbook;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;

public interface StudentService {

    void saveProfile(Student student);

    Student retrieveProfile(String studentUsername);

    void saveLogbook(Logbook logbook, Student student);
    
}
