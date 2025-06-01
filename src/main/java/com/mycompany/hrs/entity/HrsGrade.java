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
@Table(name = "HRS_GRADES", schema = "HRS")
public class HrsGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRADE_ID")
    private Long gradeId;

    @Column(name = "GRADE_CODE", length = 255)
    private String gradeCode;

    @Column(name = "GRADE_NAME", length = 255)
    private String gradeName;

    // Constructors
    public HrsGrade() { }

    public HrsGrade(Long gradeId, String gradeCode, String gradeName) {
        this.gradeId = gradeId;
        this.gradeCode = gradeCode;
        this.gradeName = gradeName;
    }

    // Getters & Setters
    public Long getGradeId() { return gradeId; }
    public void setGradeId(Long gradeId) { this.gradeId = gradeId; }

    public String getGradeCode() { return gradeCode; }
    public void setGradeCode(String gradeCode) { this.gradeCode = gradeCode; }

    public String getGradeName() { return gradeName; }
    public void setGradeName(String gradeName) { this.gradeName = gradeName; }
}