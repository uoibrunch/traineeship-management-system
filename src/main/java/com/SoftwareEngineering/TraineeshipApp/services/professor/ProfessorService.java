package com.SoftwareEngineering.TraineeshipApp.services.professor;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;

public interface ProfessorService {

    Professor retrieveProfile(String username);
    
    void saveProfile(Professor professor);

    List<TraineeshipPosition> retrieveAssignedPositions();

    void saveEvaluation(Integer positionId, Evaluation evaluation);

    void saveUsernameAndId(Professor professor);

    String extractUsernameFromUser();

    TraineeshipPosition getTraineeshipPositionById(Integer positionId);

}
