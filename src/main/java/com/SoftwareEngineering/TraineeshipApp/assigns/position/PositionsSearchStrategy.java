package com.SoftwareEngineering.TraineeshipApp.assigns.position;

import java.util.List;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

public interface PositionsSearchStrategy {

    List<TraineeshipPosition> search(String applicantUsername);

}
