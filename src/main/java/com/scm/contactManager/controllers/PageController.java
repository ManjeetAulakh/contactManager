package com.scm.contactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.contactManager.exception.UserAlreadyExists;
import com.scm.contactManager.models.Providers;
import com.scm.contactManager.models.User;
import com.scm.contactManager.payloads.UserForm;
import com.scm.contactManager.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String hello(Model model) {

        // sending data to view
        model.addAttribute("name", "Spring Tech");
        model.addAttribute("youtube", "Inside India");
        model.addAttribute("github", "htpsss");

        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/services")
    public String service() {
        return "services";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/register")
    public String signup(Model model) {
        model.addAttribute("userform", new UserForm());
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("userform") UserForm userform,BindingResult result ,Model model) {

        if (result.hasErrors()) {
            // return errors to form
            return "register";
        }

        User user = new User();
        user.setName(userform.getName());
        user.setEmail(userform.getEmail());
        user.setPassword(userform.getPassword());
        user.setAbout(userform.getAbout());
        user.setProfilePic("default.png");
        user.setPhoneNumber(userform.getPhoneNumber());
        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setPhoneVerified(false);
        user.setProvider(Providers.SELF);

        try {
            userService.registerUser(user);
            model.addAttribute("message", "Registration successful! Please login.");
            return "register-success";

        } catch (UserAlreadyExists e) {
            model.addAttribute("userform", userform);
            model.addAttribute("field", e.getFieldName());
            model.addAttribute("message", e.getMessage());
            return "register";

        } catch (Exception e) {
            model.addAttribute("userform", userform);
            model.addAttribute("generalError", "Something went wrong!");
            return "register";
        }
    }

}
