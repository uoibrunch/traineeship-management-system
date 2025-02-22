package services;

import java.util.List;

import domainmodel.Professor;
import domainmodel.TraineeshipPosition;
import domainmodel.Evaluation;

public interface ProfessorService {

    Professor retrieveProfile(String username);
    
    void saveProfile(Professor professor);

    List<TraineeshipPosition> retrieveAssignedPositions();

    void evaluateAssignedPosition(Integer positionId);

    void saveEvaluation(Integer positionId, Evaluation evaluation);

}
