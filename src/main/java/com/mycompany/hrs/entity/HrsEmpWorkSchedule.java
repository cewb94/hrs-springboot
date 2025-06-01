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
import java.time.LocalDate;

/**
 *
 * @author Yaqoub Alshatti
 */

@Entity
@Table(name = "HRS_EMP_WORK_SCHEDULES", schema = "HRS")
public class HrsEmpWorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_WORK_SCH_ID")
    private Long empWorkSchId;

    @Column(name = "WORK_SCH_ID")
    private Long workSchId;

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "EFF_START_DATE")
    private LocalDate effStartDate;

    @Column(name = "EFF_END_DATE")
    private LocalDate effEndDate;

    // Constructors
    public HrsEmpWorkSchedule() { }

    public HrsEmpWorkSchedule(Long empWorkSchId, Long workSchId, Long empId,
                              LocalDate effStartDate, LocalDate effEndDate) {
        this.empWorkSchId = empWorkSchId;
        this.workSchId = workSchId;
        this.empId = empId;
        this.effStartDate = effStartDate;
        this.effEndDate = effEndDate;
    }

    // Getters & Setters
    public Long getEmpWorkSchId() { return empWorkSchId; }
    public void setEmpWorkSchId(Long empWorkSchId) { this.empWorkSchId = empWorkSchId; }

    public Long getWorkSchId() { return workSchId; }
    public void setWorkSchId(Long workSchId) { this.workSchId = workSchId; }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public LocalDate getEffStartDate() { return effStartDate; }
    public void setEffStartDate(LocalDate effStartDate) { this.effStartDate = effStartDate; }

    public LocalDate getEffEndDate() { return effEndDate; }
    public void setEffEndDate(LocalDate effEndDate) { this.effEndDate = effEndDate; }
}