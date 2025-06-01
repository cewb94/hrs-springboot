package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsDepartment;
import com.mycompany.hrs.repository.HrsDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DepartmentService {

    private final HrsDepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(HrsDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Async
    @Cacheable(cacheNames = "departments")
    public CompletableFuture<List<HrsDepartment>> getAllDepartments() {
        List<HrsDepartment> all = departmentRepository.findAll();
        return CompletableFuture.completedFuture(all);
    }

    @Async
    @Cacheable(cacheNames = "department", key = "#deptId", condition = "#deptId != null")
    public CompletableFuture<HrsDepartment> getDepartmentById(Long deptId) {
        return CompletableFuture.supplyAsync(() ->
                departmentRepository.findById(deptId).orElse(null));
    }

    @Async
    @Transactional
    @CacheEvict(cacheNames = {"departments", "department"}, allEntries = true)
    public CompletableFuture<HrsDepartment> createDepartment(HrsDepartment d) {
        HrsDepartment saved = departmentRepository.save(d);
        return CompletableFuture.completedFuture(saved);
    }

    @Async
    @Transactional
    @CacheEvict(cacheNames = {"departments", "department"}, allEntries = true)
    public CompletableFuture<HrsDepartment> updateDepartment(Long id, HrsDepartment d) {
        return CompletableFuture.supplyAsync(() -> {
            HrsDepartment existing = departmentRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Department not found: " + id));
            existing.setLocId(d.getLocId());
            existing.setDeptName(d.getDeptName());
            return departmentRepository.save(existing);
        });
    }

    @Async
    @Transactional
    @CacheEvict(cacheNames = {"departments", "department"}, allEntries = true)
    public CompletableFuture<Void> deleteDepartment(Long id) {
        return CompletableFuture.runAsync(() -> departmentRepository.deleteById(id));
    }
}
