package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.services.student.StudentService;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;




@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/students/dashboard")
    public String getStudentDashboard(){
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.retrieveProfile(username);
        if (student == null) {
            System.out.println("no one in database");
        }
        model.addAttribute("student", student);
        return "students/dashboard";
    }

    @RequestMapping("/students/save")
    public String saveProfile(@ModelAttribute("student") Student student){
        student.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        studentService.saveProfile(student);
        
        return "redirect:/students/retrieveProfile";
    }

    public String saveLogbook(@ModelAttribute("position") TraineeshipPosition position , Model theModel){
        return null;
    }

    public String fillLogbook(@ModelAttribute("position") TraineeshipPosition position , Model thModel){
        return null;
    }

}
