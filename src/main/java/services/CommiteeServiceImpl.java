package services;

import factory.*;
import mappers.*;

import java.util.List;

import domainmodel.*;

public class CommiteeServiceImpl implements CommiteeService{

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
