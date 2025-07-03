package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.PurchaseReport;
import com.dao.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // Add new report
    public void addPurchaseReport(PurchaseReport report) {
        purchaseRepository.addPurchaseReport(report);
    }

    // Get all reports
    public List<PurchaseReport> getAllReports() {
        return purchaseRepository.getAllReports();
    }

    // Filter by date and category
    public List<PurchaseReport> getFilteredReports(LocalDate from, LocalDate to, String category) {
        return purchaseRepository.getReportsByDateAndCategory(from, to, category);
    }
}
