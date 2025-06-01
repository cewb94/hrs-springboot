/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;

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
@Table(name = "HRS_ABSENCE_TYPE", schema = "HRS")
public class HrsAbsenceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ABS_ID")
    private Long absId;

    @Column(name = "ABS_CODE", length = 255)
    private String absCode;

    @Column(name = "ABS_NAME", length = 255)
    private String absName;

    // Constructors
    public HrsAbsenceType() { }

    public HrsAbsenceType(Long absId, String absCode, String absName) {
        this.absId = absId;
        this.absCode = absCode;
        this.absName = absName;
    }

    // Getters & Setters
    public Long getAbsId() {
        return absId;
    }
    public void setAbsId(Long absId) {
        this.absId = absId;
    }
    public String getAbsCode() {
        return absCode;
    }
    public void setAbsCode(String absCode) {
        this.absCode = absCode;
    }
    public String getAbsName() {
        return absName;
    }
    public void setAbsName(String absName) {
        this.absName = absName;
    }
}