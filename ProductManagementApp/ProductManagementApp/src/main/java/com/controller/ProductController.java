package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bean.Product;
import com.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/all")
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "allProducts";
    }


    // Show all products (marketplace view)
    @GetMapping("")
    public String showMarketplace(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        System.out.println("Showing marketplace...");
        return "homePage"; // This is the name of your HTML file
    }

    // Show add product form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct"; // addProduct.html should exist
    }

    // Handle add product form
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        productService.addProduct(product);
        model.addAttribute("message", "Product added successfully!");
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    // Delete product
    @GetMapping("/delete/{pid}")
    public String deleteProduct(@PathVariable int pid) {
        productService.deleteProduct(pid);
        return "redirect:/products/all";
    }
}
