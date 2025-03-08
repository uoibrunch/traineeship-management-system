package com.SoftwareEngineering.TraineeshipApp.controllers;

import com.SoftwareEngineering.TraineeshipApp.services.company.CompanyService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        
        Company company = companyService.retrieveProfile(companyService.extractUsernameFromUser());

        model.addAttribute("company", company);

        return "company/dashboard";
    }

    @RequestMapping("/company/retrieveProfile")
    public String retrieveProfile(Model theModel){
    
		Company company = companyService.retrieveProfile(companyService.extractUsernameFromUser());

        theModel.addAttribute("company", company);

        return "company/dashboard";
    }

    @RequestMapping("/company/showFormForUpdate")
	public String showFormForUpdate(Model theModel) {
		
		Company theCompany = companyService.retrieveProfile(companyService.extractUsernameFromUser());

        if (theCompany == null) {
            theCompany = new Company();
        }
        
		theModel.addAttribute("company", theCompany);
		
		return "company/company-form";
	}

    @RequestMapping("/company/save")
    public String saveProfile(Company company , Model model){

        companyService.saveUsernameAndId(company);
       
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
       
        companyService.addPosition(companyService.extractUsernameFromUser(),position);  
        
        return "redirect:/company/retrieveProfile";
    }

    @RequestMapping("/company/positions")
    public String listAvailablePositions(Model model){

        String username = companyService.extractUsernameFromUser();

        List<TraineeshipPosition> positions = companyService.retrieveAvailablePositions(username);
        
        model.addAttribute("positions", positions);
        
        return "company/positions-list";

    }
    
    @RequestMapping("/company/assignedPositions")
    public String listAssignedPositions(Model model){

        String username = companyService.extractUsernameFromUser();
    
        List<TraineeshipPosition> assignedPositions = companyService.retrieveAssignedPositions(username);
        
        model.addAttribute("assignedPositions", assignedPositions);
        
        return "company/assigned-positions-list";
    }

    @RequestMapping("/company/selectPosition")
    public String evaluateAssignedTraineeship(Integer positionId , Model model){

        TraineeshipPosition position = companyService.getTraineeshipPositionById(positionId);

        List<Logbook> logbooks = position.getStudentLogbook();

        Evaluation evaluation = new Evaluation();

        Student student = position.getStudent();

        Company company = position.getCompany();

        model.addAttribute("position", position);

        model.addAttribute("evaluation", evaluation);

        model.addAttribute("student", student);

        model.addAttribute("logbooks", logbooks);

        model.addAttribute("company", company);

        return "company/evaluation-form"; 
    }

    @RequestMapping("/company/saveEvaluation")
    public String saveEvaluation( Integer positionId, @ModelAttribute("evaluation")Evaluation evaluation , Model model){

        companyService.saveEvaluation(positionId, evaluation);
        
        Student student = companyService.getTraineeshipPositionById(positionId).getStudent();

        model.addAttribute("evaluation", evaluation);

        model.addAttribute("student", student);
           
        return "company/show-evaluation";
        
    }

    @RequestMapping("company/delete")
    public String deletePosition(@RequestParam("positionId") int theId){

        companyService.deleteById(theId);

        return "redirect:/company/positions";
    }


}
