package factory.assignment;

import domainmodel.Professor;
import factory.supervisor.SupervisorAssignmentStrategy;
import mappers.*;

public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {

    private TraineeshipPositionsMapper positionsMapper;
    private ProfessorMapper professorMapper;
    

    public void assign(Integer positionId){
        
    }


}
