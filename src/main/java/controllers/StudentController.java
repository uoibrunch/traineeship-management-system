package controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import services.*;
import domainmodel.*;

public class StudentController {

    private StudentService studentService;

    public String getStudentDashboard(){
        return null;
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
