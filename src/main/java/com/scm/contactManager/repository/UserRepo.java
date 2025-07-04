package com.scm.contactManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.contactManager.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
