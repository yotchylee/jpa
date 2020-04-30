package com.example.jpa.entity;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private String name;

    private String description;

    @Column(name = "priceCurrency", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private String priceCurrency;


    @Column(name = "priceAmount", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private BigDecimal priceAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }
}
