package com.alobcan.eazyschool.rest;

import com.alobcan.eazyschool.model.Contact;
import com.alobcan.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact")
public class ContactRestController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {
        if (Objects.nonNull(contact) && Objects.nonNull(contact.getStatus())) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }

    }
}
