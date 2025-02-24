package com.SoftwareEngineering.TraineeshipApp.controllers;



import com.SoftwareEngineering.TraineeshipApp.services.company.CompanyService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

@Controller
public class CompanyController {

    CompanyService companyService;

    @RequestMapping("/company/dashboard")
    public String getCompanyDashboard(){
       return "company/dashboard";
    }

    public String retrieveProfile(Model model){
        return null;
    }

    public String saveProfile(Company company , Model model){
        return null;
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

}
