package factory.positions;

import java.util.List;

import domainmodel.*;

public interface PositionsSearchStrategy {

    List<TraineeshipPosition> search(String applicantUsername);

}
