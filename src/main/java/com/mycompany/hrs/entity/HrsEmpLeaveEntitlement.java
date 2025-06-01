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
@Table(name = "HRS_EMP_LEAVE_ENTITLEMENTS", schema = "HRS")
public class HrsEmpLeaveEntitlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_LV_ENT_ID")
    private Long empLvEntId;

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "ENT_DAYS_AMOUNT", length = 4000)
    private String entDaysAmount;

    @Column(name = "EFF_START_DATE")
    private Date effStartDate;

    @Column(name = "EFF_END_DATE")
    private Date effEndDate;

    // Constructors
    public HrsEmpLeaveEntitlement() { }

    public HrsEmpLeaveEntitlement(Long empLvEntId, Long empId, String entDaysAmount,
                                  Date effStartDate, Date effEndDate) {
        this.empLvEntId = empLvEntId;
        this.empId = empId;
        this.entDaysAmount = entDaysAmount;
        this.effStartDate = effStartDate;
        this.effEndDate = effEndDate;
    }

    // Getters & Setters
    public Long getEmpLvEntId() { return empLvEntId; }
    public void setEmpLvEntId(Long empLvEntId) { this.empLvEntId = empLvEntId; }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public String getEntDaysAmount() { return entDaysAmount; }
    public void setEntDaysAmount(String entDaysAmount) { this.entDaysAmount = entDaysAmount; }

    public Date getEffStartDate() { return effStartDate; }
    public void setEffStartDate(Date effStartDate) { this.effStartDate = effStartDate; }

    public Date getEffEndDate() { return effEndDate; }
    public void setEffEndDate(Date effEndDate) { this.effEndDate = effEndDate; }
}