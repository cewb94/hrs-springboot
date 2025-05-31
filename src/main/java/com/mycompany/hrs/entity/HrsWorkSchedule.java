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
@Table(name = "HRS_WORK_SCHEDULES", schema = "HRS")
public class HrsWorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_SCH_ID")
    private Long workSchId;

    @Column(name = "WORK_SCH_CODE", length = 255)
    private String workSchCode;

    @Column(name = "WORK_SCH_NAME", length = 255)
    private String workSchName;

    @Column(name = "CLOCK_IN_TIME", length = 4000)
    private String clockInTime;

    @Column(name = "CLOCK_OUT_TIME", length = 4000)
    private String clockOutTime;

    // Constructors
    public HrsWorkSchedule() { }

    public HrsWorkSchedule(Long workSchId, String workSchCode, String workSchName,
                           String clockInTime, String clockOutTime) {
        this.workSchId = workSchId;
        this.workSchCode = workSchCode;
        this.workSchName = workSchName;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
    }

    // Getters & Setters
    public Long getWorkSchId() { return workSchId; }
    public void setWorkSchId(Long workSchId) { this.workSchId = workSchId; }

    public String getWorkSchCode() { return workSchCode; }
    public void setWorkSchCode(String workSchCode) { this.workSchCode = workSchCode; }

    public String getWorkSchName() { return workSchName; }
    public void setWorkSchName(String workSchName) { this.workSchName = workSchName; }

    public String getClockInTime() { return clockInTime; }
    public void setClockInTime(String clockInTime) { this.clockInTime = clockInTime; }

    public String getClockOutTime() { return clockOutTime; }
    public void setClockOutTime(String clockOutTime) { this.clockOutTime = clockOutTime; }
}