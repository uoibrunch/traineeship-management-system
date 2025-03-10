package com.SoftwareEngineering.TraineeshipApp.services.student;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

public interface StudentService {

    void saveProfile(Student student);

    Student retrieveProfile(String studentUsername);

    void saveLogbook(Logbook logbook, Student student);

    void saveUsernameAndId(Student student);

    String extractUsernameFromUser();

    void applyForATraineeship(Student student );
    
}
