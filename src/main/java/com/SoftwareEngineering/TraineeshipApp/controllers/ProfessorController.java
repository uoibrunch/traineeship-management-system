package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.web.bind.annotation.ModelAttribute;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.services.professor.ProfessorService;

public class ProfessorController {

    ProfessorService professorService;


    public String getProfessorDashBoard(){
        return null;
    }

    public String retrieveProfile(Model model){
        return null;
    }

    public String saveProfile(@ModelAttribute("profile") Professor professor , Model theModel){
        return null;
    }

    public String listAssignedTraineeships(Model model){
        return null;
    }

    public String evaluateAssignedTraineeship(Integer positionId, Model model){
        return null;
    }

    public String saveEvaluation(Integer positionId, Evaluation evaluation , Model model){
        return null;
    }

    
}
