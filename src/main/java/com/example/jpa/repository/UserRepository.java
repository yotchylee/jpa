package com.example.jpa.repository;

import com.example.jpa.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
    long countByName(String name);
    long deleteByLastName(String lastname);
    @Transactional
    List<User> removeByLastName(String lastname);
    List<User> findByName(String name);
}