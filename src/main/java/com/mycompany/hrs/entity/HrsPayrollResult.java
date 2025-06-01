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
@Table(name = "HRS_PAYROLL_RESULTS", schema = "HRS")
public class HrsPayrollResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_RES_ID")
    private Long payResId;

    @Column(name = "PAY_RUN_ID")
    private Long payRunId;

    @Column(name = "SAL_ELEM_ID")
    private Long salElemId;

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "PAY_RES_ELEM_NAME", length = 255)
    private String payResElemName;

    // Constructors
    public HrsPayrollResult() { }

    public HrsPayrollResult(Long payResId, Long payRunId, Long salElemId, Long empId, String payResElemName) {
        this.payResId = payResId;
        this.payRunId = payRunId;
        this.salElemId = salElemId;
        this.empId = empId;
        this.payResElemName = payResElemName;
    }

    // Getters & Setters
    public Long getPayResId() { return payResId; }
    public void setPayResId(Long payResId) { this.payResId = payResId; }

    public Long getPayRunId() { return payRunId; }
    public void setPayRunId(Long payRunId) { this.payRunId = payRunId; }

    public Long getSalElemId() { return salElemId; }
    public void setSalElemId(Long salElemId) { this.salElemId = salElemId; }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public String getPayResElemName() { return payResElemName; }
    public void setPayResElemName(String payResElemName) { this.payResElemName = payResElemName; }
}