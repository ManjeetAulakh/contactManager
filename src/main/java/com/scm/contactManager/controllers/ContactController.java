package com.scm.contactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.contactManager.helper.Helper;
import com.scm.contactManager.models.Contact;
import com.scm.contactManager.models.User;
import com.scm.contactManager.payloads.ContactForm;
import com.scm.contactManager.services.ContactService;
import com.scm.contactManager.services.UserService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/add-contact" ,method = RequestMethod.GET)
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        // contactForm.setName("heelo");   // put in value atttribute of input by th atg
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add-contact";
    }

    @RequestMapping(value = "/process-contact", method=RequestMethod.POST)
    public String processContact(@Valid @ModelAttribute ContactForm contactForm, 
    BindingResult result, Authentication authentication) {

        if(result.hasErrors()){
            return "user/add-contact";
        }

        String username = Helper.getEmailOfLoggedUser(authentication);

        User user = userService.getUserByEmail(username);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setFavorite(contactForm.isFavorite());  
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);

        contactService.saveContact(contact);
        // return "redirect:/user/contacts";
        return "redirect:/user/contacts/success";

    }

    @RequestMapping("/success")
    public String addContactSuccess() {
        return "user/success";
    }
    
    
}
