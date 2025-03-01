package com.SoftwareEngineering.TraineeshipApp.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.UserDetails;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.CommitteeMember;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.services.committee.CommitteeService;
import org.springframework.ui.Model;

@Controller
public class CommitteeController {

    @Autowired
    private CommitteeService committeeService;

    @RequestMapping("/committee/dashboard")
    public String getComimmitteeDashboard(){

        CommitteeMember  committeeMember =  new CommitteeMember();

        saveUsernameAndId(committeeMember);

        return "committee/dashboard";
    }

    @RequestMapping("/committee/showTraineeshipApplications")
    public String listTrainseeshipApplications(Model model){
        List<Student> students = committeeService.retrieveTraineeshipApplications();
        model.addAttribute("students", students);
        return "committee/students-list";
    }

    @RequestMapping("/committee/findPositions")
    public String findPositions(String studentUsername , String strategy , Model model){

        Student student = committeeService.findStudentByUsername(studentUsername);

        List<TraineeshipPosition> positions = committeeService.retrievePositionsForApplicant(studentUsername, strategy);

        model.addAttribute("student", student);

        model.addAttribute("positions", positions);

        return "committee/positions-list";
    }

    @RequestMapping("/committee/selectStudent")
    public String selectStudent(@RequestParam("studentId") int theId,  Model model){

        Student student = committeeService.findStudentById(theId);

        List<TraineeshipPosition> unassignedPositions = committeeService.listUnassignedTraineeships();

        model.addAttribute("student", student);

        model.addAttribute("positions", unassignedPositions);

        return "committee/selected-student";
    }

    @RequestMapping("/committee/assignPosition")
    public String assignPosition(@RequestParam("positionId") Integer positionId, @RequestParam("studentUsername") String studentUsername, Model model) {
        
        committeeService.assignPosition(positionId, studentUsername);
            
        model.addAttribute("successMessage", "Position assigned successfully!");
       
        return "committee/dashboard";
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

     public void saveUsernameAndId(CommitteeMember committeeMember){

        committeeMember.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userDetails;
        committeeMember.setCommitteeMemberId(user.getId());


    }
    public String extractUsernameFromUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
    

}
