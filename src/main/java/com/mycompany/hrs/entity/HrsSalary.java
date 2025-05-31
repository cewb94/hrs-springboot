/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author Yaqoub Alshatti
 */
@Entity
@Table(name = "HRS_SALARIES", schema = "HRS")
public class HrsSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAL_ID")
    private Long salId;

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "EFF_START_DATE")
    private LocalDate effStartDate;

    @Column(name = "EFF_END_DATE")
    private LocalDate effEndDate;

    // Constructors
    public HrsSalary() { }

    public HrsSalary(Long salId, Long empId, LocalDate effStartDate, LocalDate effEndDate) {
        this.salId = salId;
        this.empId = empId;
        this.effStartDate = effStartDate;
        this.effEndDate = effEndDate;
    }

    // Getters & Setters
    public Long getSalId() { return salId; }
    public void setSalId(Long salId) { this.salId = salId; }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public LocalDate getEffStartDate() { return effStartDate; }
    public void setEffStartDate(LocalDate effStartDate) { this.effStartDate = effStartDate; }

    public LocalDate getEffEndDate() { return effEndDate; }
    public void setEffEndDate(LocalDate effEndDate) { this.effEndDate = effEndDate; }
}