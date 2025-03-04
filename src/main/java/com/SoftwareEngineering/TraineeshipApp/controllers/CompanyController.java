package com.SoftwareEngineering.TraineeshipApp.controllers;



import com.SoftwareEngineering.TraineeshipApp.services.company.CompanyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		Company theCompany = companyService.retrieveProfile(extractUsernameFromUser());
        
		theModel.addAttribute("company", theCompany);
		
		return "company/company-form";
	}

    @RequestMapping("/company/save")
    public String saveProfile(Company company , Model model){

        saveUsernameAndId(company);
       
        companyService.saveProfile(company);
        
        return "redirect:/company/retrieveProfile";
    }

    @RequestMapping("/company/showPositionForm")
    public String showPositionForm(Model model) {

        TraineeshipPosition position = new TraineeshipPosition();

        model.addAttribute("position", position);  

        return "company/position-form"; 

    }

    @RequestMapping("/company/savePosition")
    public String savePosition(@ModelAttribute("position") TraineeshipPosition position, Model model) {
        
        String username = extractUsernameFromUser();


        Company company = companyService.retrieveProfile(username);

      
        position.setCompany(company);

        
        position.setIsAssigned(false);

       
        companyService.addPosition(extractUsernameFromUser(),position);  
        
        return "redirect:/company/retrieveProfile";
    }

    @RequestMapping("/company/positions")
    public String listAvailablePositions(Model model){

        String username = extractUsernameFromUser();

        List<TraineeshipPosition> positions = companyService.retrieveAvailablePositions(username);
        
       model.addAttribute("positions", positions);
        
       return "company/positions-list";

    }
    
    @RequestMapping("/company/assignedPositions")
    public String listAssignedPositions(Model model){

        String username = extractUsernameFromUser();
    
        List<TraineeshipPosition> assignedPositions = companyService.retrieveAssignedPositions(username);
        
        model.addAttribute("assignedPositions", assignedPositions);
        
        return "company/assigned-positions-list";
    }

    public String evaluateAssignedTraineeship(Integer positionId, Model model){
        return null;
    }

    public String saveEvaluation(Integer positionId, Evaluation evaluation , Model model){
        return null;
    }

    @RequestMapping("company/delete")
    public String deletePosition(@RequestParam("positionId") int theId){

        companyService.deleteById(theId);

        return "redirect:/company/positions";
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
