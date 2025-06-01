/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;


import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import jakarta.persistence.*;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;


/**
 *
 * @author Yaqoub Alshatti
 */
@Entity
@Table(name = "HRS_EMPLOYEES", schema = "HRS")
public class HrsEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "EMP_NUMBER", length = 255)
    private String empNumber;

    @Column(name = "FIRST_NAME", length = 255)
    private String firstName;

    @Column(name = "LAST_NAME", length = 255)
    private String lastName;

    @Column(name = "DOB", length = 4000)
    private String dob;

    @Column(name = "GENDER", length = 4000)
    private String gender;

    @Column(name = "NATIONALITY", length = 4000)
    private String nationality;

    // Constructors
    public HrsEmployee() { }

    public HrsEmployee(Long empId, String empNumber, String firstName, String lastName,
                       String dob, String gender, String nationality) {
        this.empId = empId;
        this.empNumber = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.nationality = nationality;
    }

    // Getters & Setters
    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public String getEmpNumber() { return empNumber; }
    public void setEmpNumber(String empNumber) { this.empNumber = empNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }


    
}