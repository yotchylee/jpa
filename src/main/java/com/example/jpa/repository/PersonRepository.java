package com.example.jpa.repository;

import com.example.jpa.entity.Person;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Created by liyq on 2020/4/18.
 */

public interface PersonRepository extends MyBaseRepository<Person, Long> , QueryByExampleExecutor {
    Person findByFullName(String fullName);
}
