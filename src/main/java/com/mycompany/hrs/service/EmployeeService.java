package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsEmployee;
import com.mycompany.hrs.entity.HrsAssignment;
import com.mycompany.hrs.repository.HrsEmployeeRepository;
import com.mycompany.hrs.repository.HrsAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeService {

    private final HrsEmployeeRepository employeeRepository;
    private final HrsAssignmentRepository assignmentRepository;

    @Autowired
    public EmployeeService(HrsEmployeeRepository employeeRepository,
                           HrsAssignmentRepository assignmentRepository) {
        this.employeeRepository = employeeRepository;
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Find all employees (cached).
     */
    @Async
    @Cacheable(cacheNames = "employees")
    public CompletableFuture<List<HrsEmployee>> getAllEmployees() {
        List<HrsEmployee> all = employeeRepository.findAll();
        return CompletableFuture.completedFuture(all);
    }

    /**
     * Find one employee by ID.
     */
    @Async
    @Cacheable(cacheNames = "employee", key = "#id")
    public CompletableFuture<HrsEmployee> getEmployeeById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return employeeRepository.findById(id).orElse(null);
        });
    }

    /**
     * Create a new employee (evict cache).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee"}, allEntries = true)
    public CompletableFuture<HrsEmployee> createEmployee(HrsEmployee e) {
        HrsEmployee saved = employeeRepository.save(e);
        return CompletableFuture.completedFuture(saved);
    }

    /**
     * Update an existing employee (evict cache).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee"}, allEntries = true)
    public CompletableFuture<HrsEmployee> updateEmployee(Long id, HrsEmployee e) {
        return CompletableFuture.supplyAsync(() -> {
            HrsEmployee existing = employeeRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Employee not found: " + id));
            existing.setEmpNumber(e.getEmpNumber());
            existing.setFirstName(e.getFirstName());
            existing.setLastName(e.getLastName());
            existing.setDob(e.getDob());
            existing.setGender(e.getGender());
            existing.setNationality(e.getNationality());
            return employeeRepository.save(existing);
        });
    }

    /**
     * Delete an employee (evict cache).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee"}, allEntries = true)
    public CompletableFuture<Void> deleteEmployee(Long id) {
        return CompletableFuture.runAsync(() -> {
            employeeRepository.deleteById(id);
        });
    }

    /**
     * Special method to “hire” an employee: 
     *   • Insert into HRS_EMPLOYEES, then 
     *   • Use the generated EMP_ID to insert into HRS_ASSIGNMENTS
     * This method is transactional: if assignment fails, the new employee is rolled back.
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee"}, allEntries = true)
    public CompletableFuture<HrsEmployee> hireEmployee(HrsEmployee employee, HrsAssignment assignment) {
        return CompletableFuture.supplyAsync(() -> {
            // 1) save employee
            HrsEmployee savedEmp = employeeRepository.save(employee);
            // 2) set the generated empId on assignment
            assignment.setEmpId(savedEmp.getEmpId());
            assignmentRepository.save(assignment);
            return savedEmp;
        });
    }
}
