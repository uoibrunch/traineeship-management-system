package com.SoftwareEngineering.TraineeshipApp.factory.positions;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

public interface PositionsSearchStrategy {

    List<TraineeshipPosition> search(String applicantUsername);

}
