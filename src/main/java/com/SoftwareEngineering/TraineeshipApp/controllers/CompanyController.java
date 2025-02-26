package com.SoftwareEngineering.TraineeshipApp.controllers;



import com.SoftwareEngineering.TraineeshipApp.services.company.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping("/company/dashboard")
    public String getStudentDashboard(Model model){
        
        Company company = companyService.retrieveProfile(extractUsernameFromUser());
        model.addAttribute("company", company);

        return "company/dashboard";
    }

    @RequestMapping("/company/retrieveProfile")
    public String retrieveProfile(Model theModel){
    
		Company company = companyService.retrieveProfile(extractUsernameFromUser());

        theModel.addAttribute("company", company);

        return "company/dashboard";
    }

    @RequestMapping("/company/showFormForUpdate")
	public String showFormForUpdate(Model theModel) {
		
		Company theCompany = new Company();
        
		theModel.addAttribute("company", theCompany);
		
		return "company/company-form";
	}

    

    @RequestMapping("/company/save")
    public String saveProfile(Company company , Model model){
        saveUsernameAndId(company);
       
        companyService.saveProfile(company);
        
        return "redirect:/company/retrieveProfile";
    }

    public String listAvailablePositions(Model model){
        return null;
    }

    public String showPositionForm(Model model){
        return null;
    }

    public String savePosition(TraineeshipPosition position , Model model){
        return null;
    }

    public String listAssignedPositions(Model model){
        return null;
    }

    public String evaluateAssignedTraineeship(Integer positionId, Model model){
        return null;
    }

    public String saveEvaluation(Integer positionId, Evaluation evaluation , Model model){
        return null;
    }

    public String deletePosition(Integer postionId, Model model){
        return null;
    }

    public void saveUsernameAndId(Company company){

        company.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userDetails;
        company.setCompanyId(user.getId());

    }

    public String extractUsernameFromUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

}
