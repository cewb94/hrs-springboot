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
@Table(name = "HRS_ASSIGNMENTS", schema = "HRS")
public class HrsAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSI_ID")
    private Long assiId;

    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "GRADE_ID")
    private Long gradeId;

    @Column(name = "SUPER_ID")
    private Long superId; // optional

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "ASSI_NUMBER", length = 255)
    private String assiNumber;

    @Column(name = "ASSI_ACTION", length = 255)
    private String assiAction;

    @Column(name = "EFF_START_DATE")
    private Date effStartDate;

    @Column(name = "EFF_END_DATE")
    private Date effEndDate;

    // Constructors
    public HrsAssignment() { }

    public HrsAssignment(Long assiId, Long deptId, Long jobId, Long gradeId, Long superId,
                         Long empId, String assiNumber, String assiAction,
                         Date effStartDate, Date effEndDate) {
        this.assiId = assiId;
        this.deptId = deptId;
        this.jobId = jobId;
        this.gradeId = gradeId;
        this.superId = superId;
        this.empId = empId;
        this.assiNumber = assiNumber;
        this.assiAction = assiAction;
        this.effStartDate = effStartDate;
        this.effEndDate = effEndDate;
    }

    // Getters & Setters
    public Long getAssiId() { return assiId; }
    public void setAssiId(Long assiId) { this.assiId = assiId; }

    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getGradeId() { return gradeId; }
    public void setGradeId(Long gradeId) { this.gradeId = gradeId; }

    public Long getSuperId() { return superId; }
    public void setSuperId(Long superId) { this.superId = superId; }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public String getAssiNumber() { return assiNumber; }
    public void setAssiNumber(String assiNumber) { this.assiNumber = assiNumber; }

    public String getAssiAction() { return assiAction; }
    public void setAssiAction(String assiAction) { this.assiAction = assiAction; }

    public Date getEffStartDate() { return effStartDate; }
    public void setEffStartDate(Date effStartDate) { this.effStartDate = effStartDate; }

    public Date getEffEndDate() { return effEndDate; }
    public void setEffEndDate(Date effEndDate) { this.effEndDate = effEndDate; }
}