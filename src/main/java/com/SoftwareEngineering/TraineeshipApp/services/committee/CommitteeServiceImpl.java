package com.SoftwareEngineering.TraineeshipApp.services.committee;

import com.SoftwareEngineering.TraineeshipApp.factory.*;
import com.SoftwareEngineering.TraineeshipApp.factory.positions.PositionsSearchFactory;
import com.SoftwareEngineering.TraineeshipApp.factory.search.PositionsSearchStrategy;
import com.SoftwareEngineering.TraineeshipApp.factory.supervisor.SupervisorAssignmentFactory;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

@Service
public class CommitteeServiceImpl implements CommitteeService{

    @Autowired
    private PositionsSearchFactory positionsSearchFactory;

    // @Autowired
    // private SupervisorAssignmentFactory supervisorAssignementFactory;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;


    @Override
    public List<Student> retrieveTraineeshipApplications(){

        return studentMapper.findByLookingForTraineeshipTrue() ;

    }

    @Override
    public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy){
        PositionsSearchStrategy searchStrategy = positionsSearchFactory.create(strategy);
        return searchStrategy.search(applicantUsername);
    }

    @Override
    public Student findStudentById(int id){

        return studentMapper.findById(id);
        
    }

    @Override
    public List <TraineeshipPosition> listUnassignedTraineeships(){

        return positionsMapper.findByIsAssignedFalse();

    }

    @Override
    public Student findStudentByUsername(String username) {

        Student student = studentMapper.findByUsername(username);

        return student;
    }

    @Override
    public void assignPosition(Integer positionId, String studentUsername){

    }

    @Override
    public void assignSupervisor(Integer positionId, String strategy){

    }

    @Override
    public List<TraineeshipPosition> listAssignedTraineeships(){
        return null;
    }

    @Override
    public void completeAssignedTraineeships(Integer positionId){

    }

  

}
