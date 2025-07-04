package com.scm.contactManager.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.contactManager.exception.UserAlreadyExists;
import com.scm.contactManager.models.User;
import com.scm.contactManager.repository.UserRepo;
import com.scm.contactManager.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User registerUser(User user) {
        // Check if user already exists
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExists("email" ,"User with this email already exists");
        }
        
        // Save the user to the repository
        if (userRepo.findByPhoneNumber(user.getPhoneNumber()) != null) {
            throw new UserAlreadyExists("phoneNumber" ,"User with this phone number already exists");
        }

        String uid = UUID.randomUUID().toString();
        user.setUserId(uid);
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        // Update the user information
        User existingUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAbout(user.getAbout());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());  
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setProvider(user.getProvider());

        return userRepo.save(existingUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public void deleteUser(String userId) {
        if (!userRepo.existsById(userId)) {
            throw new IllegalArgumentException("User with this ID does not exist");
        }
        userRepo.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean isUserExists(String userId) {
        return userRepo.existsById(userId);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber) != null;
    }
    
}
