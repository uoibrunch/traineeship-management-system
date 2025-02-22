package services;

import domainmodel.Student;
import domainmodel.TraineeshipPosition;

public interface StudentService {

    void saveProfile(Student student);
    Student retrieveProfile(String studentUsername);
    void saveLogbook(TraineeshipPosition position);

}
