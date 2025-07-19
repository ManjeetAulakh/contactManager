package com.scm.contactManager.services;

import java.util.List;

import com.scm.contactManager.models.Contact;

public interface ContactService {
    
    Contact saveContact(Contact contact);
    Contact updateContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getByID(Long id);
    void deleteContact(Long id);
    List<Contact> getByUserId(String id);
}
