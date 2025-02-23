package com.SoftwareEngineering.TraineeshipApp.factory.assignment;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.factory.supervisor.SupervisorAssignmentStrategy;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {

    private TraineeshipPositionsMapper positionsMapper;
    private ProfessorMapper professorMapper;
    

    public void assign(Integer positionId){
        
    }


}
