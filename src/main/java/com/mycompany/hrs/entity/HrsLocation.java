/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;

//import jakarta.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Yaqoub Alshatti
 */
@Entity
@Table(name = "HRS_LOCATIONS", schema = "HRS")
public class HrsLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOC_ID")
    private Long locId;

    @Column(name = "POSTAL_CODE", length = 4000)
    private String postalCode;

    @Column(name = "STREET_ADDRESS", length = 4000)
    private String streetAddress;

    @Column(name = "CITY", length = 4000)
    private String city;

    @Column(name = "STATE", length = 4000)
    private String state;

    @Column(name = "COUNTRY", length = 4000)
    private String country;

    // Constructors
    public HrsLocation() { }

    public HrsLocation(Long locId, String postalCode, String streetAddress, String city, String state, String country) {
        this.locId = locId;
        this.postalCode = postalCode;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    // Getters & Setters
    public Long getLocId() { return locId; }
    public void setLocId(Long locId) { this.locId = locId; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}