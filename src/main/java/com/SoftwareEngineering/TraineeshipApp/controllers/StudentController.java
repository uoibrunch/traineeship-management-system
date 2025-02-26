package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.services.student.StudentService;
import com.SoftwareEngineering.TraineeshipApp.services.user.UserService;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
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
        model.addAttribute("student", student);

        return "students/dashboard";
    }

    @RequestMapping("/students/showFormForUpdate")
	public String showFormForAdd(Model theModel) {
		
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

    public String saveLogbook(@ModelAttribute("position") TraineeshipPosition position , Model theModel){
        return null;
    }

    public String fillLogbook(@ModelAttribute("position") TraineeshipPosition position , Model thModel){
        return null;
    }

}
