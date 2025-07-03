package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Add product
    @Transactional
    public void addProduct(Product product) {
        entityManager.persist(product);
    }
    
    public Product getProductById(int pid) {
        return entityManager.find(Product.class, pid);
    }

    // Delete product by ID
    @Transactional
    public void deleteProduct(int pid) {
        Product product = entityManager.find(Product.class, pid);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    // Get all products
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                            .getResultList();
    }
}
