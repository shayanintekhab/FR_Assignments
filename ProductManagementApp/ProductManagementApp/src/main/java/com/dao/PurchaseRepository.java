package com.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.PurchaseReport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class PurchaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // ✅ Add a new purchase report
    @Transactional
    public void addPurchaseReport(PurchaseReport report) {
        entityManager.persist(report);
    }

    // ✅ Get all purchase reports
    public List<PurchaseReport> getAllReports() {
        return entityManager
                .createQuery("SELECT p FROM PurchaseReport p", PurchaseReport.class)
                .getResultList();
    }

    // ✅ Filter purchase reports by date range and/or category
    public List<PurchaseReport> getReportsByDateAndCategory(LocalDate fromDate, LocalDate toDate, String category) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM PurchaseReport p WHERE 1=1");

        if (fromDate != null) {
            jpql.append(" AND p.date >= :fromDate");
        }
        if (toDate != null) {
            jpql.append(" AND p.date <= :toDate");
        }
        if (category != null && !category.isEmpty()) {
            jpql.append(" AND p.category = :category");
        }

        TypedQuery<PurchaseReport> query = entityManager.createQuery(jpql.toString(), PurchaseReport.class);

        if (fromDate != null) {
            query.setParameter("fromDate", fromDate.toString());
        }
        if (toDate != null) {
            query.setParameter("toDate", toDate.toString());
        }
        if (category != null && !category.isEmpty()) {
            query.setParameter("category", category);
        }

        return query.getResultList();
    }
}
