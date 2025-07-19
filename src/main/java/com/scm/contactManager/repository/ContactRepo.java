package com.scm.contactManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.contactManager.models.Contact;
import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{
    List<Contact> findByUser_UserId(String id);
}
