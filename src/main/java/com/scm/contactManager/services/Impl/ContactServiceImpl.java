package com.scm.contactManager.services.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.contactManager.exception.ResourceNotFoundException;
import com.scm.contactManager.models.Contact;
import com.scm.contactManager.repository.ContactRepo;
import com.scm.contactManager.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        String uid = UUID.randomUUID().toString();
        contact.setId(uid);
        return contactRepo.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getByID(Long id) {
        return contactRepo.findById(id).orElseThrow(() -> 
                        new ResourceNotFoundException("Contact wiht this ID not exists"));
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepo.findById(id).orElseThrow(() -> 
                        new ResourceNotFoundException("Contact wiht this ID not exists"));
        contactRepo.delete(contact);

    }

    @Override
    public List<Contact> getByUserId(String id) {
        List<Contact> contacts = contactRepo.findByUser_UserId(id);
        return contacts;
    }
    
}
