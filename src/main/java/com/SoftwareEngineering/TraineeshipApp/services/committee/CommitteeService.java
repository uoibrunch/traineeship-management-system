package com.SoftwareEngineering.TraineeshipApp.services.committee;

import java.util.List;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

public interface CommitteeService {
    List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String Strategy);

    List<Student> retrieveTraineeshipApplications();

    void assignPosition(Integer positionId, String studentUsername);

    void assignSupervisor(Integer positionId, String strategy);

    List <TraineeshipPosition> listAssignedTraineeships();

    void completeAssignedTraineeships(Integer positionId);

    Student findStudentById(int id);

    List <TraineeshipPosition> listUnassignedTraineeships();

    Student findStudentByUsername(String username);

    TraineeshipPosition findPositionById(int id);

    List <Professor> listProfessors();

    void saveUsernameAndId(CommitteeMember committeeMember);
    
    String extractUsernameFromUser();

}
