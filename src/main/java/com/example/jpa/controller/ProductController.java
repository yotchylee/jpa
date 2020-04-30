package com.example.jpa.controller;

import com.example.jpa.entity.Product;
import com.example.jpa.entity.WrappedEntity.Products;
import com.example.jpa.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

/**
 * Created by liyq on 2020/4/21.
 */
@Api(value = "ProductController", tags = {"ProductController"})
@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "增加Product（一个对象参数）", response = Product.class)
    @PostMapping(path = "/add")
    public @ResponseBody
    Product addNewUser(@RequestBody Product product) {
        return  productRepository.save(product);
    }

    @GetMapping(path = "/totalPriceContaining/{text}")
    @ApiOperation(value = "查询product总价", response = BigDecimal.class)
    public @ResponseBody
    BigDecimal getAllUsers(@PathVariable(name="text") String text) {
        Products products = productRepository.findAllByDescriptionContaining(text);
        return products.getTotal();
    }
}
