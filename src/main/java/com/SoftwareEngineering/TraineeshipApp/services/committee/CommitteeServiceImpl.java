package com.SoftwareEngineering.TraineeshipApp.services.committee;

import com.SoftwareEngineering.TraineeshipApp.factory.*;
import com.SoftwareEngineering.TraineeshipApp.factory.positions.PositionsSearchFactory;
import com.SoftwareEngineering.TraineeshipApp.factory.search.PositionsSearchStrategy;
import com.SoftwareEngineering.TraineeshipApp.factory.supervisor.SupervisorAssignmentFactory;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ProfessorMapper professorMapper;


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

    public TraineeshipPosition findPositionById(int id){

        return positionsMapper.findById(id);
        
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
    public void assignPosition(Integer positionId, String studentUsername) {
    
        Student student = studentMapper.findByUsername(studentUsername);
        
        if (student == null) {
            throw new IllegalArgumentException("Student with username " + studentUsername + " not found.");
        }

        Optional<TraineeshipPosition> positionOpt = positionsMapper.findById(positionId);
        
        if (!positionOpt.isPresent()) {
            throw new IllegalArgumentException("Traineeship Position with ID " + positionId + " not found.");
        }

        TraineeshipPosition position = positionOpt.get();
        
        if (position.isAssigned()) {
            throw new IllegalStateException("Traineeship Position with ID " + positionId + " is already assigned.");
        }

        position.setStudent(student);

        position.setIsAssigned(true);

        student.setLookingForTraineeship(false);

        student.setAssignedTraineeship(position);

        positionsMapper.save(position);

        studentMapper.save(student);
    }


    @Override
    public List<TraineeshipPosition> listAssignedTraineeships(){

        List<TraineeshipPosition> assignedPositions = positionsMapper.findByIsAssignedTrueAndIsSupervisedFalse();;

        return assignedPositions;
    }

    @Override
    public List <Professor> listProfessors(){

       return professorMapper.findAll();

    }


    @Override
    public void assignSupervisor(Integer positionId, String strategy){

    }

   

    @Override
    public void completeAssignedTraineeships(Integer positionId){

    }

  

}
