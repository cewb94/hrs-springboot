package com.mycompany.hrs.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String nationality; // th:field="*{nationality}" where *{...} is Selection expressions.

    // ASSIGNMENT fields
    private Long deptId;
    private Long jobId;
    private Long gradeId;
    private Long supervisorId; // optional
    private String assiNumber;
    private String assiAction;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effEndDate;

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

    public Date getEffStartDate() { return effStartDate; }
    public void setEffStartDate(Date effStartDate) { this.effStartDate = effStartDate; }

    public Date getEffEndDate() { return effEndDate; }
    public void setEffEndDate(Date effEndDate) { this.effEndDate = effEndDate; }
}
