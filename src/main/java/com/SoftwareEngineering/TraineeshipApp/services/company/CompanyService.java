package com.SoftwareEngineering.TraineeshipApp.services.company;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;


public interface CompanyService {

    Company retrievePofile(String username);

    void saveProfile(Company company);

    List<TraineeshipPosition> retrieveAvailablePositions(String username);

    void addPosition(String username, TraineeshipPosition position);

    List<TraineeshipPosition> retrieveAssignedPositions(String username);

    void evaluateAssignedPosition(Integer positionId);

    void saveEvaluation(Integer positionId, Evaluation evaluation);

}
