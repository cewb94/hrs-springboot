package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsDepartment;
import com.mycompany.hrs.entity.HrsLocation;
import com.mycompany.hrs.repository.HrsDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @CachePut(cacheNames = "departments")
    public CompletableFuture<List<HrsDepartment>> getAllDepartments() {
        List<HrsDepartment> all = departmentRepository.findAll();
        return CompletableFuture.completedFuture(all);
    }

    @Async
    @CachePut(cacheNames = "department", key = "#deptId", condition = "#deptId != null")
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
    public CompletableFuture<HrsDepartment> updateDepartment(Long id, HrsDepartment dept) {
        return CompletableFuture.supplyAsync(() -> {
            HrsDepartment existing = departmentRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Department not found with id: " + id));
            //existing.setLocId(d.getLocId());
            existing.setLocation(dept.getLocation());
            existing.setDeptName(dept.getDeptName());
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
