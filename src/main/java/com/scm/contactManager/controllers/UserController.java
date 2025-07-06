package com.scm.contactManager.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
    
    // user dashboard
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashboard(){
        return "user/dashboard";
    }

    // user profile
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile(){
        return "user/user-profile";
    }
}
