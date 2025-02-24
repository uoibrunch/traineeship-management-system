package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.services.professor.ProfessorService;

@Controller
public class ProfessorController {

    ProfessorService professorService;

    @RequestMapping("/professor/dashboard")
    public String getProfessorDashBoard(){
        return "professor/dashboard";
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
