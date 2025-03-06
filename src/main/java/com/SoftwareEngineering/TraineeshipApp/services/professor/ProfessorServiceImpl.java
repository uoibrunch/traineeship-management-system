package com.SoftwareEngineering.TraineeshipApp.services.professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.mappers.EvaluationMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.ProfessorMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.EvaluationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ProfessorServiceImpl  implements ProfessorService{

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired 
    private TraineeshipPositionsMapper traineeshipPositionMapper;

    @Autowired
    private EvaluationMapper  evaluationMapper ;

    

    @Override
    public Professor retrieveProfile(String username){

        return professorMapper.findByUsername(username);

    }

    @Override
    public void saveProfile(Professor professor){
        
        Professor existingProfessor = professorMapper.findByUsername(professor.getUsername());
        if (existingProfessor != null) {
            professor.setProfessorId(existingProfessor.getProfessorId());
        }

        professorMapper.save(professor);

    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(){

        String username = extractUsernameFromUser();

        Professor professor = professorMapper.findByUsername(username);

        if (professor != null){

            return professor.getSupervisedPositions();
            
        }

        return  new ArrayList<>();
        
    }

    @Override
    public void saveEvaluation(Integer positionId, Evaluation evaluation){

        TraineeshipPosition position = getTraineeshipPositionById(positionId);

        if (position == null) {
            throw new RuntimeException("Traineeship position not found.");
        }
    
        String professorUsername = extractUsernameFromUser();
        Professor professor = professorMapper.findByUsername(professorUsername);
    
        if (professor == null) {
            throw new RuntimeException("Company not found.");
        }

        Optional<Evaluation> existingEvaluation = evaluationMapper.findByTraineeshipPositionAndEvaluationType(position, EvaluationType.PROFESSOR);
    
        if (existingEvaluation.isPresent()) {
            Evaluation evalToUpdate = existingEvaluation.get();
            evalToUpdate.setMotivation(evaluation.getMotivation());
            evalToUpdate.setEfficiency(evaluation.getEfficiency());
            evalToUpdate.setEffectiveness(evaluation.getEffectiveness());
            evaluationMapper.save(evalToUpdate); 
        } else {
            evaluation.setTraineeshipPosition(position);
            evaluation.setEvaluationType(EvaluationType.PROFESSOR);
            evaluationMapper.save(evaluation);  
        }
    }

    @Override
    public void saveUsernameAndId(Professor professor){

        professor.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userDetails;
        professor.setProfessorId(user.getId());

    }

    @Override
    public String extractUsernameFromUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    @Override
    public TraineeshipPosition getTraineeshipPositionById(Integer positionId) {
        return traineeshipPositionMapper.findById(positionId)
            .orElseThrow(() -> new RuntimeException("Traineeship position not found"));
    }



}
