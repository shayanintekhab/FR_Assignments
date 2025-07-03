package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bean.User;
import com.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public String showAllUsers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<User> users;

        if (keyword != null && !keyword.trim().isEmpty()) {
            users = userService.searchUsers(keyword);
            model.addAttribute("keyword", keyword); // retain search term
        } else {
            users = userService.getAllUsers();
        }

        model.addAttribute("users", users);
        return "users";
    }

//    @GetMapping("/users")
//    public String showAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "users"; // maps to users.html
//    }

    // ✅ Show add user form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add_user"; // Create add_user.html
    }

    // ✅ Handle form submission to add user
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    // ✅ Show form to update password
    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("user", new User()); // only email and new password needed
        return "change_password"; // Create change_password.html
    }

    // ✅ Handle password update
    @PostMapping("/change-password")
    public String updatePassword(@RequestParam String email, @RequestParam String password, Model model) {
        boolean updated = userService.updatePassword(email, password);
        model.addAttribute("success", updated);
        return "change_password";
    }
    
    @PostMapping("/search")
    public String searchUsers(@RequestParam("keyword") String keyword, Model model) {
        List<User> matchedUsers = userService.searchUsers(keyword);
        model.addAttribute("users", matchedUsers);
        model.addAttribute("keyword", keyword);
        return "userSearchResults";  // userSearchResults.html - create this view
    }
}
