package services.committee;
import java.util.List;

import domainmodel.*;

public interface CommitteeService {
    List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String Strategy);

    List<Student> retrieveTraineeshipApplications();

    void assignPosition(Integer positionId, String studentUsername);

    void assignSupervisor(Integer positionId, String strategy);

    List <TraineeshipPosition> listAssignedTraineeships();

    void completeAssignedTraineeships(Integer positionId);

}
