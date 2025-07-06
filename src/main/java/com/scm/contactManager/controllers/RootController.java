package com.scm.contactManager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.contactManager.models.User;
import com.scm.contactManager.services.UserService;
import com.scm.contactManager.helper.Helper;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {

        if (authentication == null) {
            return;
        }

        System.out.println("Adding logged user in information of model");

        String username = Helper.getEmailOfLoggedUser(authentication);
        logger.info("user Logged in {}", username);

        // fetch user from db
        User user = userService.getUserByEmail(username);
        if (user == null) {
            model.addAttribute("loggedInUser", null);
        } else {
            System.out.println(user);
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser", user);
        }

    }
}
