/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;

import jakarta.persistence.*;
import java.util.Date;

import org.hibernate.annotations.Immutable;

/**
 * We mark the entity @Immutable to tell Hibernate it’s a read‐only view.
 * We choose ASSI_ID as the primary key (it is unique in the view).
 * @author Yaqoub Alshatti
 */
@Entity
@Immutable
@Table(name = "HRS_ASG_DETAILS_V3", schema = "HRS")
public class HrsAsgDetailsV3 {

    @Id
    @Column(name = "ASSI_ID")
    private Long assiId;

    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "GRADE_ID")
    private Long gradeId;

    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "ASSI_NUMBER", length = 255)
    private String assiNumber;

    @Column(name = "ASSI_ACTION", length = 255)
    private String assiAction;

    @Column(name = "EFF_START_DATE")
    private java.util.Date effStartDate;

    @Column(name = "EFF_END_DATE")
    private java.util.Date effEndDate;

    @Column(name = "EMP_NUMBER", length = 255)
    private String empNumber;

    @Column(name = "FULL_NAME", length = 511)
    private String fullName;

    @Column(name = "JOB_CODE", length = 255)
    private String jobCode;

    @Column(name = "JOB_TITLE", length = 4000)
    private String jobTitle;

    @Column(name = "GRADE_CODE", length = 255)
    private String gradeCode;

    @Column(name = "GRADE_NAME", length = 255)
    private String gradeName;

    @Column(name = "SUPERVISOR_ID")
    private Long supervisorId;

    @Column(name = "SUPERVISOR_NAME", length = 511)
    private String supervisorName;

    @Column(name = "DEPARTMENT_NAME", length = 255)
    private String departmentName;
    
    @Column(name = "SAL_ID")
    private Long salId;
    
    @Column(name = "SAL_START_DATE")
    private java.util.Date salStartDate;
    
    @Column(name = "SAL_END_DATE")
    private java.util.Date salEndDate;

    // Note: Because this is a read‐only view, we do not generate setters for every field; 
    //       but Hibernate requires a no‐arg constructor.
    public HrsAsgDetailsV3() { }

    // Getters
    public Long getAssiId() { return assiId; }
    public Long getDeptId() { return deptId; }
    public Long getJobId() { return jobId; }
    public Long getGradeId() { return gradeId; }
    public Long getEmpId() { return empId; }
    public String getAssiNumber() { return assiNumber; }
    public String getAssiAction() { return assiAction; }
    public java.util.Date getEffStartDate() { return effStartDate; }
    public java.util.Date getEffEndDate() { return effEndDate; }
    public String getEmpNumber() { return empNumber; }
    public String getFullName() { return fullName; }
    public String getJobCode() { return jobCode; }
    public String getJobTitle() { return jobTitle; }
    public String getGradeCode() { return gradeCode; }
    public String getGradeName() { return gradeName; }
    public Long getSupervisorId() { return supervisorId; }
    public String getSupervisorName() { return supervisorName; }
    public String getDepartmentName() { return departmentName; }

    public Long getSalId() { return salId; }
    public Date getSalStartDate() { return salStartDate; }
    public Date getSalEndDate() { return salEndDate; }
    
}