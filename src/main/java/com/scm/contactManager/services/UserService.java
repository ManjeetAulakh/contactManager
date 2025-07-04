package com.scm.contactManager.services;

import java.util.List;
import java.util.Optional;

import com.scm.contactManager.models.User;


public interface UserService {

    User registerUser(User user);
    User updateUser(User user);
    User getUserByEmail(String email);
    Optional<User> getUserById(String userId);
    void deleteUser(String userId);
    List<User> getAllUsers();

    boolean isUserExists(String userId);
    boolean isEmailExists(String email);
    boolean isPhoneNumberExists(String phoneNumber);

}
