package com.platform.momentum.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    @Enumerated(EnumType.ORDINAL)
    private ProductType type;
    private String name;
    private Double current_balance;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "investor_id")
    private Investor investor;

    public Product() {
    }

    public Product(Integer product_id, ProductType type, String name, Double current_balance, Investor investor) {
        this.product_id = product_id;
        this.type = type;
        this.name = name;
        this.current_balance = current_balance;
        this.investor = investor;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Double current_balance) {
        this.current_balance = current_balance;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
}
