package com.example.jpa.controller;

import com.example.jpa.entity.Person;
import com.example.jpa.repository.MyBaseRepository;
import com.example.jpa.repository.PersonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * Created by liyq on 2020/4/18.
 */

@Api(value = "PersonController", tags = {"PersonController"})
@Controller // This means that this class is a Controller
@RequestMapping(path = "/person") // This means URL's start with /demo (after Application path)
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MyBaseRepository<Person,Long> myBaseRepository;
    @ApiOperation(value = "(选择性暴露接口)增加person", response = Person.class)
    @PostMapping(path = "/person/add")
    public @ResponseBody
    Person addPerson(@RequestBody Person person) {
        //PersonRepository 只可以使用findById、save、findByFullName三个方法
        return myBaseRepository.save(person);
    }

    @ApiOperation(value = "(选择性暴露接口)通过ID查询 person【@NoRepositoryBean】", response = Person.class)
    @GetMapping(path = "/findPerson/id/{id}")
    public @ResponseBody Person findPerson(@PathVariable("id") Long id) {
        //PersonRepository 只可以使用findById、save、findByFullName三个方法
        Optional<Person> o = personRepository.findById(id);
        return o.get();
    }

    @ApiOperation(value = "通过fullName查询 person", response = Person.class)
    @GetMapping(path = "/findPerson/fullName/{fullName}")
    public @ResponseBody Person findPerson(@PathVariable("fullName") String fullName) {
        return personRepository.findByFullName(fullName);
    }

    @ApiOperation(value = "通过fullName匹配查询 person【ExampleMatcher】", response = List.class)
    @GetMapping(path = "/findPerson/fullNameMatcher/{fullName}")
    public @ResponseBody Iterable<Person>  findPersonExampleMatcher(@PathVariable("fullName") String nameStr) {
        Person person = new Person();
        person.setFullName(nameStr);
        person.setLastName(nameStr);

        ExampleMatcher matcher1 = ExampleMatcher.matching()
                .withIgnorePaths("nationality")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withMatcher("lastName", startsWith());
//        ExampleMatcher matcher2 = ExampleMatcher.matching()
////                .withMatcher("firstName", endsWith())
//                .withMatcher("fullName", match -> match.startsWith())
//                .withMatcher("lastName", startsWith().ignoreCase());
        Example<Person> example1 = Example.of(person, matcher1);
//        Example<Person> example2 = Example.of(person, matcher2);
        return personRepository.findAll(example1);
    }

}
