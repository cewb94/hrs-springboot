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
@Table(name = "HRS_DEPARTMENTS", schema = "HRS")
public class HrsDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "LOC_ID")
    private Long locId;

    @Column(name = "DEPT_NAME", length = 255)
    private String deptName;

    // Constructors
    public HrsDepartment() { }

    public HrsDepartment(Long deptId, Long locId, String deptName) {
        this.deptId = deptId;
        this.locId = locId;
        this.deptName = deptName;
    }

    // Getters & Setters
    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }

    public Long getLocId() { return locId; }
    public void setLocId(Long locId) { this.locId = locId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}