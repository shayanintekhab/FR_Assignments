package com.controller;

import com.bean.Product;
import com.bean.User;
import com.service.ProductService;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService; // ✅ Added to fetch products

    // ✅ Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "index"; // index.html
    }

    // ✅ Handle login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              Model model) {
        User loggedInUser = userService.getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (loggedInUser != null) {
            // Admin route
            if ("admin".equalsIgnoreCase(loggedInUser.getType())) {
                return "admin"; // admin.html
            } else {
                // Normal user: fetch and add products to model
                List<Product> products = productService.getAllProducts();
                model.addAttribute("products", products);
                System.out.println("Loaded products for homepage: " + products.size());
                return "homePage"; // homePage.html
            }
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "index"; // index.html with error
        }
    }

    // ✅ Show signup page
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // signup.html
    }

    // ✅ Handle signup form
    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, Model model) {
        userService.addUser(user);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        if(user.getType().equals("admin")) {
        	return "admin"; // After signup, show home page with products
        }
        else {
        	return "homePage";
        }
    }

    // ✅ Show change password page
    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "changePassword"; // changePassword.html
    }

    // ✅ Handle password update
    @PostMapping("/change-password")
    public String updatePassword(@RequestParam String email,
                                 @RequestParam String newPassword,
                                 Model model) {
        boolean updated = userService.updatePassword(email, newPassword);
        model.addAttribute(updated ? "message" : "error",
                updated ? "Password updated!" : "User not found.");
        return "changePassword";
    }
}
