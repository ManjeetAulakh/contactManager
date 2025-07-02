package com.scm.contactManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
    public String signup() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
