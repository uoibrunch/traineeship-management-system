package com.SoftwareEngineering.TraineeshipApp.services.professor;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.mappers.ProfessorMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ProfessorServiceImpl  implements ProfessorService{

    @Autowired
    private ProfessorMapper professorMapper;

    @Override
    public Professor retrieveProfile(String username){
        return professorMapper.findByUsername(username);
    }

    @Override
    public void saveProfile(Professor professor){
        professorMapper.save(professor);
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(){
        return null;
    }

    @Override
    public void evaluateAssignedPosition(Integer postitionId){

    }

    @Override
    public void saveEvaluation(Integer positionId, Evaluation evaluation){

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



}
