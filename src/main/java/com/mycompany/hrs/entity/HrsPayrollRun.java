/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "HRS_PAYROLL_RUNS", schema = "HRS")
public class HrsPayrollRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_RUN_ID")
    private Long payRunId;

    @Column(name = "PAY_RUN_PERIOD", length = 255)
    private String payRunPeriod;

    // Constructors
    public HrsPayrollRun() { }

    public HrsPayrollRun(Long payRunId, String payRunPeriod) {
        this.payRunId = payRunId;
        this.payRunPeriod = payRunPeriod;
    }

    // Getters & Setters
    public Long getPayRunId() { return payRunId; }
    public void setPayRunId(Long payRunId) { this.payRunId = payRunId; }

    public String getPayRunPeriod() { return payRunPeriod; }
    public void setPayRunPeriod(String payRunPeriod) { this.payRunPeriod = payRunPeriod; }
}