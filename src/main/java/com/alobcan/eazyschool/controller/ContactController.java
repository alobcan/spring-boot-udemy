package com.alobcan.eazyschool.controller;

import com.alobcan.eazyschool.model.Contact;
import com.alobcan.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@Slf4j
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if(errors.hasErrors()) {
            log.error("Contact form validation failed due to: " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model) {
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }
}
