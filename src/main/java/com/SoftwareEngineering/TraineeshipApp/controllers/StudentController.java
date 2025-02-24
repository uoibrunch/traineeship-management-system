package com.SoftwareEngineering.TraineeshipApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SoftwareEngineering.TraineeshipApp.services.student.StudentService;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

@Controller
public class StudentController {

    private StudentService studentService;

    @RequestMapping("/student/dashboard")
    public String getStudentDashboard(){
        return "student/dashboard";
    }

    public String retrieveProfile(Model model){
        return null;
    }

    public String saveProfile(@ModelAttribute("student") Student student , Model theModel){
        return null;
    }

    public String fillLogbook(Model model){
        return null;
    }

    public String saveLogbook(@ModelAttribute("position") TraineeshipPosition position , Model theModel){
        return null;
    }

}
