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
@Table(name = "HRS_COMP_ELEMENTS", schema = "HRS")
public class HrsCompElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMP_ELEM_ID")
    private Long compElemId;

    @Column(name = "ELEM_NAME", length = 255)
    private String elemName;

    @Column(name = "ELEM_TYPE", length = 255)
    private String elemType;

    @Column(name = "ELEM_VAL_METHOD", length = 255)
    private String elemValMethod;

    @Column(name = "ELEM_RECURRENCE", length = 255)
    private String elemRecurrence;

    @Column(name = "ELEM_CODE", length = 255)
    private String elemCode;

    // Constructors
    public HrsCompElement() { }

    public HrsCompElement(Long compElemId, String elemName, String elemType, String elemValMethod, String elemRecurrence, String elemCode) {
        this.compElemId = compElemId;
        this.elemName = elemName;
        this.elemType = elemType;
        this.elemValMethod = elemValMethod;
        this.elemRecurrence = elemRecurrence;
        this.elemCode = elemCode;
    }

    // Getters & Setters
    public Long getCompElemId() { return compElemId; }
    public void setCompElemId(Long compElemId) { this.compElemId = compElemId; }

    public String getElemName() { return elemName; }
    public void setElemName(String elemName) { this.elemName = elemName; }

    public String getElemType() { return elemType; }
    public void setElemType(String elemType) { this.elemType = elemType; }

    public String getElemValMethod() { return elemValMethod; }
    public void setElemValMethod(String elemValMethod) { this.elemValMethod = elemValMethod; }

    public String getElemRecurrence() { return elemRecurrence; }
    public void setElemRecurrence(String elemRecurrence) { this.elemRecurrence = elemRecurrence; }

    public String getElemCode() { return elemCode; }
    public void setElemCode(String elemCode) { this.elemCode = elemCode; }
}