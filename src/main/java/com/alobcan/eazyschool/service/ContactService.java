package com.alobcan.eazyschool.service;

import com.alobcan.eazyschool.constants.EazySchoolConstants;
import com.alobcan.eazyschool.model.Contact;
import com.alobcan.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        Contact savedContact = contactRepository.save(contact);
        log.info(contact.toString());
        return (null != savedContact && savedContact.getContactId() > 0);
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
        Page<Contact> contacts = contactRepository.findByStatusWithQuery(EazySchoolConstants.OPEN, pageable);
        return contacts;
    }

    public boolean updateMsgStatus(int id) {
        return contactRepository.updateStatusById(EazySchoolConstants.CLOSE, id) > 0;
    }
}
