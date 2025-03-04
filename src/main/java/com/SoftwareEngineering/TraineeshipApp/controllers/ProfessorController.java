package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Professor professor = professorService.retrieveProfile(extractUsernameFromUser());
        model.addAttribute("professor", professor);
        return "professor/dashboard";
    }

    @RequestMapping("/professor/retrieveProfile")
    public String retrieveProfile(Model model){
        Professor professor = professorService.retrieveProfile(extractUsernameFromUser());

        model.addAttribute("professor", professor);

        return "professor/dashboard";
    }

    @RequestMapping("/professor/showFormForUpdate")
	public String showFormForUpdateString(Model theModel) {

		Professor theProfessor = professorService.retrieveProfile(extractUsernameFromUser());

		theModel.addAttribute("professor", theProfessor);
		
		return "professor/professor-form";
	}

    @RequestMapping("/professor/save")
    public String saveProfile(@ModelAttribute("profile") Professor professor , Model theModel){
        saveUsernameAndId(professor);
       
        professorService.saveProfile(professor);
        
        return "redirect:/professor/retrieveProfile";
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

    public void saveUsernameAndId(Professor professor){

        professor.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userDetails;
        professor.setProfessorId(user.getId());

    }

    public String extractUsernameFromUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }


    
}
