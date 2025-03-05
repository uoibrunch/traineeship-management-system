package com.SoftwareEngineering.TraineeshipApp.services.company;

import java.util.List;
import java.util.Optional;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;


public interface CompanyService {

    Company retrieveProfile(String username);

    void saveProfile(Company company);

    List<TraineeshipPosition> retrieveAvailablePositions(String username);

    void addPosition(String username, TraineeshipPosition position);

    List<TraineeshipPosition> retrieveAssignedPositions(String username);

    void evaluateAssignedPosition(Integer positionId);

    void saveEvaluation(Integer positionId, Evaluation evaluation);

    void deleteById(int id);

    void saveUsernameAndId(Company company);

    String extractUsernameFromUser();

    TraineeshipPosition getTraineeshipPositionById(Integer positionId);

    Optional<Evaluation> getEvaluationForPosition(Integer positionId);

}
