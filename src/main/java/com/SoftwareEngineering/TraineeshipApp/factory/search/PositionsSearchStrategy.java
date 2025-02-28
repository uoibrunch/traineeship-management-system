package com.SoftwareEngineering.TraineeshipApp.factory.search;


import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

public interface PositionsSearchStrategy {

    List<TraineeshipPosition> search(String applicantUsername);

}
