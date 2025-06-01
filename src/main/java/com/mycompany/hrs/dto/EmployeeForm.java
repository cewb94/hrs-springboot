package com.mycompany.hrs.dto;

import java.time.LocalDate;

/**
 * DTO for “Hire Employee” form: 
 *   • Fields for HrsEmployee 
 *   • Fields for HrsAssignment 
 */
public class EmployeeForm {
    // EMPLOYEE fields
    private String empNumber;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String nationality;

    // ASSIGNMENT fields
    private Long deptId;
    private Long jobId;
    private Long gradeId;
    private Long supervisorId; // optional
    private String assiNumber;
    private String assiAction;
    private LocalDate effStartDate;
    private LocalDate effEndDate;

    public EmployeeForm() { }

    // Getters & Setters
    public String getEmpNumber() { return empNumber; }
    public void setEmpNumber(String empNumber) { this.empNumber = empNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getGradeId() { return gradeId; }
    public void setGradeId(Long gradeId) { this.gradeId = gradeId; }

    public Long getSupervisorId() { return supervisorId; }
    public void setSupervisorId(Long supervisorId) { this.supervisorId = supervisorId; }

    public String getAssiNumber() { return assiNumber; }
    public void setAssiNumber(String assiNumber) { this.assiNumber = assiNumber; }

    public String getAssiAction() { return assiAction; }
    public void setAssiAction(String assiAction) { this.assiAction = assiAction; }

    public LocalDate getEffStartDate() { return effStartDate; }
    public void setEffStartDate(LocalDate effStartDate) { this.effStartDate = effStartDate; }

    public LocalDate getEffEndDate() { return effEndDate; }
    public void setEffEndDate(LocalDate effEndDate) { this.effEndDate = effEndDate; }
}
