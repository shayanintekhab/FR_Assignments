package com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Product;
import com.bean.PurchaseReport;
import com.service.ProductService;
import com.service.PurchaseService;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @PostMapping("/buy")
    public String handleBuy(@RequestParam int pid, RedirectAttributes redirectAttributes) {
        // Fetch the product by its ID
        Product product = productService.getProductById(pid);

        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found.");
            return "redirect:/products";
        }

        // Create and populate the PurchaseReport
        PurchaseReport report = new PurchaseReport();
        report.setPid(pid);
        report.setUemail("test@example.com"); // Replace with actual user session email
        report.setDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        report.setCategory(product.getCategory()); // Set category from fetched product

        // Save to DB
        purchaseService.addPurchaseReport(report);

        redirectAttributes.addFlashAttribute("message", "Product bought!");
        return "redirect:/products";
    }
    
    
    // ✅ Show form to add purchase
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("purchase", new PurchaseReport());
        return "add_purchase"; // create this thymeleaf form
    }

    // ✅ Handle form submit
    @PostMapping("/add")
    public String addPurchase(@ModelAttribute("purchase") PurchaseReport purchase) {
        purchaseService.addPurchaseReport(purchase);
        return "redirect:/purchases";
    }

    // ✅ Display all or filtered purchase reports
    @PostMapping("/filter")
    public String viewReports(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String category,
            Model model) {

        List<PurchaseReport> reports = purchaseService.getFilteredReports(from, to, category);
        model.addAttribute("reports", reports);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("category", category);

        return "purchaseReports"; // create this view
    }
}
