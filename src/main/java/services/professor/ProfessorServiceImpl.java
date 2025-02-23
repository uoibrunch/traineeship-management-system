package services.professor;

import java.util.List;

import domainmodel.Professor;
import mappers.ProfessorMapper;
import domainmodel.TraineeshipPosition;
import domainmodel.User;
import domainmodel.Evaluation;

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
