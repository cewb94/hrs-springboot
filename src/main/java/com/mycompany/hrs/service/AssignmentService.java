package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsAssignment;
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
public class AssignmentService {

    private final HrsAssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(HrsAssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Async
    @Cacheable(cacheNames = "assignments")
    public CompletableFuture<List<HrsAssignment>> getAllAssignments() {
        List<HrsAssignment> all = assignmentRepository.findAll();
        return CompletableFuture.completedFuture(all);
    }

    @Async
    @Cacheable(cacheNames = "assignment", key = "#id", condition = "#id != null")
    public CompletableFuture<HrsAssignment> getAssignmentById(Long id) {
        return CompletableFuture.supplyAsync(() -> assignmentRepository.findById(id).orElse(null));
    }

    @Async
    @Transactional
    @CacheEvict(cacheNames = {"assignments", "assignment"}, allEntries = true)
    public CompletableFuture<HrsAssignment> createAssignment(HrsAssignment a) {
        HrsAssignment saved = assignmentRepository.save(a);
        return CompletableFuture.completedFuture(saved);
    }

    @Async
    @Transactional
    @CacheEvict(cacheNames = {"assignments", "assignment"}, allEntries = true)
    public CompletableFuture<HrsAssignment> updateAssignment(Long id, HrsAssignment a) {
        return CompletableFuture.supplyAsync(() -> {
            HrsAssignment existing = assignmentRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Assignment not found: " + id));
            existing.setDeptId(a.getDeptId());
            existing.setJobId(a.getJobId());
            existing.setGradeId(a.getGradeId());
            existing.setSuperId(a.getSuperId());
            existing.setEmpId(a.getEmpId());
            existing.setAssiNumber(a.getAssiNumber());
            existing.setAssiAction(a.getAssiAction());
            existing.setEffStartDate(a.getEffStartDate());
            existing.setEffEndDate(a.getEffEndDate());
            return assignmentRepository.save(existing);
        });
    }

    @Async
    @Transactional
    @CacheEvict(cacheNames = {"assignments", "assignment"}, allEntries = true)
    public CompletableFuture<Void> deleteAssignment(Long id) {
        return CompletableFuture.runAsync(() -> assignmentRepository.deleteById(id));
    }
}
