package com.SoftwareEngineering.TraineeshipApp.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.services.committee.CommitteeService;

import org.springframework.ui.Model;

@Controller
public class CommitteeController {

    @Autowired
    private CommitteeService committeeService;

    @RequestMapping("/committee/dashboard")
    public String getComimmitteeDashboard(){

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

    @RequestMapping("/committee/showAssignedTraineeship")
    public String listAssignedTraineeships(Model  model){
        List<TraineeshipPosition> assignedPositions = committeeService.listAssignedTraineeships();
        model.addAttribute("positions", assignedPositions);
        return "committee/assignedPositions-list";
    }

    
    @RequestMapping("/committee/selectPosition")
    public String selectPosition(@RequestParam("traineeshipId") int theId,  Model model){

        TraineeshipPosition selectedPosition = committeeService.findPositionById(theId);

        List<Professor> professors = committeeService.listProfessors();

        model.addAttribute("position", selectedPosition);

        model.addAttribute("professors", professors);

        return "committee/selected-professor";
    }

    @RequestMapping("/committee/assignProfessor")
    public String assignProfessor(@RequestParam("traineeshipId") Integer positionId,@RequestParam("strategy") String strategy, Model model){

        if (positionId == null) {
            model.addAttribute("errorMessage", "Traineeship ID is missing.");
            return "committee/assignment_error"; // Redirect to an error page
        }

        committeeService.assignSupervisor(positionId, strategy);

        TraineeshipPosition position = committeeService.findPositionById(positionId);
        if (position == null) {
            model.addAttribute("errorMessage", "Traineeship position not found.");
            return "committee/assignment_error";
        }

        Professor assignedProfessor = position.getSupervisor();
        if (assignedProfessor == null) {
            model.addAttribute("errorMessage", "No professor was assigned to this traineeship position.");
            return "committee/assignment_error";
        }

        model.addAttribute("professor", assignedProfessor);
        model.addAttribute("position", position);

        return "committee/assigned_professor";
    }

    @RequestMapping("/committee/completeTraineeship")
    public String completeAssignedTraineedships(@RequestParam("traineeshipId") Integer positionId , Model model){

        TraineeshipPosition position =  committeeService.findPositionById(positionId);

        List<Logbook> logbooks = position.getStudentLogbook();
        
        Evaluation evaluation = new Evaluation();

        Student student = position.getStudent();

        Company company = position.getCompany();

        model.addAttribute("position", position);

        model.addAttribute("evaluation", evaluation);

        model.addAttribute("student", student);

        model.addAttribute("logbooks", logbooks);

        model.addAttribute("company", company);
    
        return "committee/complete-form";
    }

    @RequestMapping(value = "/committee/updateGrade/{id}", method = RequestMethod.POST)
    public String updateGrade(@PathVariable("id") int traineeshipId, 
                              @RequestParam("grade") boolean grade , Model model ) {
    
       TraineeshipPosition position = committeeService.completeAssignedTraineeships(traineeshipId , grade);

        model.addAttribute("position", position);

        return "/committee/traineeshipResult";
    }

}
