package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.services.student.StudentService;
import com.SoftwareEngineering.TraineeshipApp.services.user.UserService;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;




@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/students/dashboard")
    public String getStudentDashboard(Model model){
        
        Student student = studentService.retrieveProfile(extractUsernameFromUser());

        if (student != null && student.getAssignedTraineeship() != null) {
            
            List<Logbook> logbooks = student.getAssignedTraineeship().getStudentLogbook();

            model.addAttribute("logbooks", logbooks);

        }

        model.addAttribute("student", student);

        return "students/dashboard";
    }

    @RequestMapping("/students/showFormForUpdate")
	public String showFormForUpdate(Model theModel) {
		
		// create model attribute to bind form data
		Student theStudent = new Student();
        
		
		theModel.addAttribute("student", theStudent);
		
		return "students/student-form";
	}

    @RequestMapping("/students/retrieveProfile")
    public String retrieveProfile(Model model){

        Student student = studentService.retrieveProfile(extractUsernameFromUser());

        model.addAttribute("student", student);

        return "students/dashboard";

    }

    @RequestMapping("/students/save")
    public String saveProfile(@ModelAttribute("student") Student student){

        saveUsernameAndId(student);
       
        studentService.saveProfile(student);
        
        return "redirect:/students/retrieveProfile";
    }

    @RequestMapping("/students/fillLogbook")
    public String fillLogbook(Model model) {

        model.addAttribute("logbook", new Logbook());

        return("students/fill-logbook");

    }

    @RequestMapping(value = "/students/saveLogbook")
    public String saveLogbook(@ModelAttribute("logbook") Logbook logbook){
    
        Student student = studentService.retrieveProfile(extractUsernameFromUser());

        studentService.saveLogbook(logbook , student);

        return "redirect:/students/dashboard"; 
    }

    public void saveUsernameAndId(Student student){

        student.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) userDetails;
        
        student.setStudentId(user.getId());

    }

    public String extractUsernameFromUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

}
