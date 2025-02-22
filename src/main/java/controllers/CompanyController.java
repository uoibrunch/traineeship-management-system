package controllers;

import services.*;
import domainmodel.*;

public class CompanyController {

    CompanyService companyService;

    public String getCompanyDashboard(){
        return null;
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
