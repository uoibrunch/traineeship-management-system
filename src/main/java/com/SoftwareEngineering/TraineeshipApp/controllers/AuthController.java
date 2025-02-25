package com.SoftwareEngineering.TraineeshipApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.services.user.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    //private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/login")
        public String login(){
        return "auth/signin";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, BindingResult result ,Model model){
        if (result.hasErrors()) {
            System.out.println("Binding Errors: " + result.getAllErrors());
        }
        System.out.println("Received user: " + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole());
        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/signin";
    }

    @RequestMapping("/")
    public String home(Model model) {
        return "homepage";
    }

}
