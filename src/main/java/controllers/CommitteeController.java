package controllers;


import services.committee.CommitteeService;

public class CommitteeController {

    private CommitteeService CommitteeService;


    public String getComimmitteeDashboard(){
        return null;
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
