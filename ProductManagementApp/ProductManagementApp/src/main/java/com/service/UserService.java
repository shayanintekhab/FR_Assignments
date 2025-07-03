package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.dao.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Add user
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Update password
    public boolean updatePassword(String email, String newPassword) {
        return userRepository.updatePassword(email, newPassword);
    }

    // Search users by keyword
    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }
}
