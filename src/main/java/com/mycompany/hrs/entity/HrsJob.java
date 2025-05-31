/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;

import jakarta.persistence.*;

/**
 *
 * @author Yaqoub Alshatti
 */

@Entity
@Table(name = "HRS_JOBS", schema = "HRS")
public class HrsJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "JOB_CODE", length = 255)
    private String jobCode;

    @Column(name = "JOB_TITLE", length = 4000)
    private String jobTitle;

    // Constructors
    public HrsJob() { }

    public HrsJob(Long jobId, String jobCode, String jobTitle) {
        this.jobId = jobId;
        this.jobCode = jobCode;
        this.jobTitle = jobTitle;
    }

    // Getters & Setters
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobCode() { return jobCode; }
    public void setJobCode(String jobCode) { this.jobCode = jobCode; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
}