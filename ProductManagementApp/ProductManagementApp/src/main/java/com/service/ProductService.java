package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Product;
import com.dao.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // ✅ Add a new product
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }
    
    public Product getProductById(int pid) {
        return productRepository.getProductById(pid);
    }

    // ✅ Delete a product by ID
    public void deleteProduct(int pid) {
        productRepository.deleteProduct(pid);
    }

    // ✅ Get all products
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}

