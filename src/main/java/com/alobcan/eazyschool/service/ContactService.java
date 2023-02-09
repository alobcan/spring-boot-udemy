package com.alobcan.eazyschool.service;

import com.alobcan.eazyschool.model.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Slf4j
@SessionScope
@Getter
@Setter
public class ContactService {
    private int counter = 0;

    public ContactService() {
        System.out.println("Contact service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        // TODO - Persist data in DB
        log.info(contact.toString());
        return  isSaved;
    }
}
