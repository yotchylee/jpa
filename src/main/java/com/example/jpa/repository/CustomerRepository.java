package com.example.jpa.repository;

import java.util.Collection;
import java.util.List;
import com.example.jpa.entity.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  Customer findById(long id);

  Collection<NamesOnly> findByFirstName(String firstName);
}