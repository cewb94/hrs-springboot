package com.mycompany.hrs.controller;

import com.mycompany.hrs.entity.HrsEmployee;
import com.mycompany.hrs.entity.HrsAssignment;
import com.mycompany.hrs.dto.EmployeeForm;
import com.mycompany.hrs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET /api/employees
    @GetMapping
    public CompletableFuture<ResponseEntity<List<HrsEmployee>>> getAllEmployees() {
        return employeeService.getAllEmployees()
                .thenApply(ResponseEntity::ok);
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsEmployee>> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .thenApply(emp -> emp != null
                        ? ResponseEntity.ok(emp)
                        : ResponseEntity.notFound().build());
    }

    // POST /api/employees
    @PostMapping
    public CompletableFuture<ResponseEntity<HrsEmployee>> createEmployee(@RequestBody HrsEmployee e) {
        return employeeService.createEmployee(e)
                .thenApply(saved -> ResponseEntity.ok(saved));
    }

    // PUT /api/employees/{id}
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsEmployee>> updateEmployee(
            @PathVariable Long id, @RequestBody HrsEmployee e) {
        return employeeService.updateEmployee(id, e)
                .thenApply(updated -> ResponseEntity.ok(updated));
    }

    // DELETE /api/employees/{id}
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id)
                .thenApply(v -> ResponseEntity.noContent().build());
    }

    // POST /api/employees/hire
    // Expects JSON like:
    // {
    //   "employee": { empNumber, firstName, lastName, dob, gender, nationality },
    //   "assignment": { deptId, jobId, gradeId, superId, assiNumber, assiAction, effStartDate, effEndDate }
    // }
    @PostMapping("/hire")
    public CompletableFuture<ResponseEntity<HrsEmployee>> hireEmployee(@RequestBody EmployeeForm form) {
        HrsEmployee e = new HrsEmployee();
        e.setEmpNumber(form.getEmpNumber());
        e.setFirstName(form.getFirstName());
        e.setLastName(form.getLastName());
        e.setDob(form.getDob());
        e.setGender(form.getGender());
        e.setNationality(form.getNationality());

        HrsAssignment a = new HrsAssignment();
        a.setDeptId(form.getDeptId());
        a.setJobId(form.getJobId());
        a.setGradeId(form.getGradeId());
        a.setSuperId(form.getSupervisorId());
        a.setAssiNumber(form.getAssiNumber());
        a.setAssiAction(form.getAssiAction());
        a.setEffStartDate(form.getEffStartDate());
        a.setEffEndDate(form.getEffEndDate());

        return employeeService.hireEmployee(e, a)
                .thenApply(savedEmp -> ResponseEntity.ok(savedEmp));
    }
}
