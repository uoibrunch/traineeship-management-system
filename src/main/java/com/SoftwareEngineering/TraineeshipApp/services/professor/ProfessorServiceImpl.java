package com.SoftwareEngineering.TraineeshipApp.services.professor;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.mappers.ProfessorMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;

public class ProfessorServiceImpl  implements ProfessorService{


    private ProfessorMapper professorMapper;


    public Professor retrieveProfile(String username){
        return null;
    }

    public void saveProfile(Professor professor){}

    public List<TraineeshipPosition> retrieveAssignedPositions(){
        return null;
    }

    public void evaluateAssignedPosition(Integer postitionId){

    }

    public void saveEvaluation(Integer positionId, Evaluation evaluation){

    }



}
