package com.platform.momentum.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer address_id;
    private String streetAddress;
    private String surbub;
    private String city;
    private String zipCode;
    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "investor_id")
    private Investor investor;

    public Address() {
    }

    public Address(Integer address_id, String streetAddress, String city, String surbub, String zipCode) {
        this.address_id = address_id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.surbub = surbub;
        this.zipCode = zipCode;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getSurbub() {
        return surbub;
    }

    public void setSurbub(String surbub) {
        this.surbub = surbub;
    }
}
