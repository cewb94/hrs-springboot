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
import java.util.Date;

/**
 *
 * @author Yaqoub Alshatti
 */
@Entity
@Table(name = "HRS_SALARY_ELEMENTS", schema = "HRS")
public class HrsSalaryElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAL_ELEM_ID")
    private Long salElemId;

    @Column(name = "SAL_ID")
    private Long salId;

    @Column(name = "COMP_ELEM_ID")
    private Long compElemId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    // Constructors
    public HrsSalaryElement() { }

    public HrsSalaryElement(Long salElemId, Long salId, Long compElemId,
                            Double amount, Date startDate, Date endDate) {
        this.salElemId = salElemId;
        this.salId = salId;
        this.compElemId = compElemId;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters & Setters
    public Long getSalElemId() { return salElemId; }
    public void setSalElemId(Long salElemId) { this.salElemId = salElemId; }

    public Long getSalId() { return salId; }
    public void setSalId(Long salId) { this.salId = salId; }

    public Long getCompElemId() { return compElemId; }
    public void setCompElemId(Long compElemId) { this.compElemId = compElemId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}