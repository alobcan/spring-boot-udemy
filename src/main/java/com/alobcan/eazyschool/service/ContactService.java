package com.alobcan.eazyschool.service;

import com.alobcan.eazyschool.constants.EazySchoolConstants;
import com.alobcan.eazyschool.model.Contact;
import com.alobcan.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Contact savedContact = contactRepository.save(contact);
        log.info(contact.toString());
        return (null != savedContact && savedContact.getContactId() > 0);
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contacts = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contacts;
    }

    public boolean updateMsgStatus(int id, String name) {
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
            contact1.setUpdatedBy(name);
            contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        return Objects.nonNull(updatedContact.getUpdatedBy());
    }
}
