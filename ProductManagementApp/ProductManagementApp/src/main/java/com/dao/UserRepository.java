package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // ✅ Add new user
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    // ✅ Get all users
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                            .getResultList();
    }

    // ✅ Update password by email
    @Transactional
    public boolean updatePassword(String email, String newPassword) {
        User user = entityManager.find(User.class, email);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    // ✅ Search users by name or email (partial match)
    public List<User> searchUsers(String keyword) {
        return entityManager.createQuery(
                "SELECT u FROM User u WHERE LOWER(u.name) LIKE :kw OR LOWER(u.email) LIKE :kw",
                User.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }
}
