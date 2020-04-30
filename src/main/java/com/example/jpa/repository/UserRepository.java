package com.example.jpa.repository;

import com.example.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
    long countByName(String name);
    long deleteByLastName(String lastname);
    @Transactional
    List<User> removeByLastName(String lastName);
    List<User> findByName(String name);

//    Page<User> findByLastName(String lastName, Pageable pageable);

    Slice<User> findByLastName(String lastName, Pageable pageable);

    List<User> findByLastName(String lastName, Sort sort);

//    List<User> findByLastName(String lastName, Pageable pageable);
}