package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.services.committee.CommitteeService;
import org.springframework.ui.Model;

@Controller
public class CommitteeController {

    private CommitteeService CommitteeService;

    @RequestMapping("/committee/dashboard")
    public String getComimmitteeDashboard(){
        return "committe/dashboard";
    }

    public String listTrainseeshipApplications(Model model){
        return null;
    }

    public String findPositions(String studentUsername , String strategy , Model model){
        return null;
    }

    public String assignPosition(Integer positionId, String studentUsername , Model model){
        return null;
    }

    public String assignSupervisor(Integer positionId, String strategy, Model model){
        return null;
    }

    public String listAssignedTraineeships(Model  model){
        return null;
    }

    public String completeAssignedTraineedships(Integer positionId , Model model){
        return null;
    }
    

}
