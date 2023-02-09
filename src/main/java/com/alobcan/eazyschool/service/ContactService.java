package com.alobcan.eazyschool.service;

import com.alobcan.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        // TODO - Persist data in DB
        log.info(contact.toString());
        return  isSaved;
    }
}
