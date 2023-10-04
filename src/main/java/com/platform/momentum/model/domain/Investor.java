package com.platform.momentum.model.domain;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investor")
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer investor_id;
    private String firstname;
    private String lastname;
    private LocalDate birth_date;
    private String phone;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(targetEntity = Product.class,
            cascade = CascadeType.ALL)
    @JoinColumn(name= "ip_fk", referencedColumnName = "investor_id")
    private List<Product> products = new ArrayList<>() ;

    public Investor() {
    }

    public Investor(Integer investor_id, String firstname, String lastname, LocalDate birth_date, String phone, String email, Address address, List<Product> products) {
        this.investor_id = investor_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.products = products;

    }

    public Integer getInvestor_id() {
        return investor_id;
    }

    public void setInvestor_id(Integer investor_id) {
        this.investor_id = investor_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
