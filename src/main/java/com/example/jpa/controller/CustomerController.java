package com.example.jpa.controller;

import com.example.jpa.repository.CustomerRepository;
import com.example.jpa.repository.NamesOnly;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by liyq on 2020/4/18.
 */

@Api(value = "CustomerController", tags = {"CustomerController"})
@Controller // This means that this class is a Controller
@RequestMapping(path = "/customer") // This means URL's start with /demo (after Application path)

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation(value = "通过firstName查询 customer【projections功能】", response = Collection.class)
    @GetMapping(path = "/namesOnly/{firstName}")
    public @ResponseBody
    Collection<NamesOnly> findCustomerNameOnly(@PathVariable("firstName") String firstName) {
        //只查定义的两个name属性
        return customerRepository.findByFirstName(firstName);
    }

}
