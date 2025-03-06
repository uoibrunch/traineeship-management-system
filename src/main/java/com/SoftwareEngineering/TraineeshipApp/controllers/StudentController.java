package com.SoftwareEngineering.TraineeshipApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.SoftwareEngineering.TraineeshipApp.services.student.StudentService;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/students/dashboard")
    public String getStudentDashboard(Model model){
        
        Student student = studentService.retrieveProfile(studentService.extractUsernameFromUser());

        if (student != null && student.getAssignedTraineeship() != null) {
            
            List<Logbook> logbooks = student.getAssignedTraineeship().getStudentLogbook();

            model.addAttribute("logbooks", logbooks);

        }

        if (student == null) {
            student = new Student();
        }

        model.addAttribute("student", student);

        return "students/dashboard";
    }

    @RequestMapping("/students/applyForATraineeship")
	public String applyForATraineeship(Model theModel) {

        Student student = studentService.retrieveProfile(studentService.extractUsernameFromUser());
    
        studentService.applyForATraineeship(student);

        return "redirect:/students/dashboard ";
    }


    @RequestMapping("/students/showFormForUpdate")
	public String showFormForUpdate(Model theModel) {
		
		Student theStudent = studentService.retrieveProfile(studentService.extractUsernameFromUser());

        if (theStudent == null) {
            theStudent = new Student();
        }
		
		theModel.addAttribute("student", theStudent);
		
		return "students/student-form";
	}

    @RequestMapping("/students/retrieveProfile")
    public String retrieveProfile(Model model){

        Student student = studentService.retrieveProfile(studentService.extractUsernameFromUser());

        model.addAttribute("student", student);

        return "students/dashboard";

    }

    @RequestMapping("/students/save")
    public String saveProfile(@ModelAttribute("student") Student student){

        Student theStudent = studentService.retrieveProfile(studentService.extractUsernameFromUser());

        if (theStudent != null){
            student.setLookingForTraineeship(theStudent.isLookingForTraineeship());
        }
 
        studentService.saveUsernameAndId(student);
       
        studentService.saveProfile(student);

        
        return "redirect:/students/dashboard";
    }

    @RequestMapping("/students/fillLogbook")
    public String fillLogbook(Model model) {

        model.addAttribute("logbook", new Logbook());

        return("students/fill-logbook");

    }

    @RequestMapping(value = "/students/saveLogbook")
    public String saveLogbook(@ModelAttribute("logbook") Logbook logbook){
    
        Student student = studentService.retrieveProfile(studentService.extractUsernameFromUser());

        studentService.saveLogbook(logbook , student);

        return "redirect:/students/dashboard"; 
    }

}
