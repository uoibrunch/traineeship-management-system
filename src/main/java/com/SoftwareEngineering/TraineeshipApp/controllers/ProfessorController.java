package com.SoftwareEngineering.TraineeshipApp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.services.professor.ProfessorService;
import org.springframework.ui.Model;

@Controller
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @RequestMapping("/professor/dashboard")
    public String getProfessorDashBoard(Model model){
        Professor professor = professorService.retrieveProfile(professorService.extractUsernameFromUser());
        model.addAttribute("professor", professor);
        return "professor/dashboard";
    }

    @RequestMapping("/professor/retrieveProfile")
    public String retrieveProfile(Model model){
        Professor professor = professorService.retrieveProfile(professorService.extractUsernameFromUser());

        model.addAttribute("professor", professor);

        return "professor/dashboard";
    }

    @RequestMapping("/professor/showFormForUpdate")
	public String showFormForUpdateString(Model theModel) {

		Professor theProfessor = professorService.retrieveProfile(professorService.extractUsernameFromUser());

        if (theProfessor == null) {
            theProfessor = new Professor();
        }

		theModel.addAttribute("professor", theProfessor);
		
		return "professor/professor-form";
	}

    @RequestMapping("/professor/save")
    public String saveProfile(@ModelAttribute("profile") Professor professor , Model theModel){
        professorService.saveUsernameAndId(professor);
       
        professorService.saveProfile(professor);
        
        return "redirect:/professor/retrieveProfile";
    }

    @RequestMapping("/professor/assignedTraineeships")
    public String listAssignedTraineeships(Model model) {
        
        List<TraineeshipPosition> traineeships = professorService.retrieveAssignedPositions();
        
        model.addAttribute("traineeships", traineeships);

        return "professor/assigned-traineeships";
        
    }


    public String evaluateAssignedTraineeship(Integer positionId, Model model){
        return null;
    }

    public String saveEvaluation(Integer positionId, Evaluation evaluation , Model model){
        return null;
    }

    
}
