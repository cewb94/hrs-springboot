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
@Table(name = "HRS_EMP_ABSENCES", schema = "HRS")
public class HrsEmpAbsence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ABS_ID")
    private Long empAbsId;

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "ABS_ID", nullable = false)
    private Long absId;

    // Constructors
    public HrsEmpAbsence() { }

    public HrsEmpAbsence(Long empAbsId, Long empId, Date startDate, Date endDate, Long absId) {
        this.empAbsId = empAbsId;
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.absId = absId;
    }

    // Getters & Setters
    public Long getEmpAbsId() { return empAbsId; }
    public void setEmpAbsId(Long empAbsId) { this.empAbsId = empAbsId; }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Long getAbsId() { return absId; }
    public void setAbsId(Long absId) { this.absId = absId; }
}