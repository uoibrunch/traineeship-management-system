package factory.assignment;

import factory.supervisor.SupervisorAssignmentStrategy;
import mappers.*;

public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy{

    private TraineeshipPositionsMapper positionsMapper;
    
    private ProfessorMapper professorMapper;

    public void assign(Integer positionId){

    }

}
