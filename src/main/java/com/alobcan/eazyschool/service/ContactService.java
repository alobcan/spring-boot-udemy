package com.alobcan.eazyschool.service;

import com.alobcan.eazyschool.constants.EazySchoolConstants;
import com.alobcan.eazyschool.model.Contact;
import com.alobcan.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact) {
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        log.info(contact.toString());
        return (result > 0);
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contacts = contactRepository.findMsgWithStatus(EazySchoolConstants.OPEN);
        return contacts;
    }
}
