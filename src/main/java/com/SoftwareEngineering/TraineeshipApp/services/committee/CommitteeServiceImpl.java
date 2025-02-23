package com.SoftwareEngineering.TraineeshipApp.services.committee;

import com.SoftwareEngineering.TraineeshipApp.factory.*;
import com.SoftwareEngineering.TraineeshipApp.factory.positions.PositionsSearchFactory;
import com.SoftwareEngineering.TraineeshipApp.factory.supervisor.SupervisorAssignmentFactory;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

public class CommitteeServiceImpl implements CommitteeService{

    private PositionsSearchFactory positionsSearchFactory;

    private SupervisorAssignmentFactory supervisorAssignementFactory;

    private StudentMapper studentMapper;

    private TraineeshipPositionsMapper positionsMapper;





    public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String Strategy){
        return null;
    }

    public List<Student> retrieveTraineeshipApplications(){
        return null;
    }

    public void assignPosition(Integer positionId, String studentUsername){

    }

    public void assignSupervisor(Integer positionId, String strategy){

    }

    public List<TraineeshipPosition> listAssignedTraineeships(){
        return null;
    }

    public void completeAssignedTraineeships(Integer positionId){

    }

}
