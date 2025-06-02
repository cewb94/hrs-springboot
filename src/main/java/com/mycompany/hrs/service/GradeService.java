package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsGrade;
import com.mycompany.hrs.repository.HrsGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GradeService {

    private final HrsGradeRepository gradeRepository;

    @Autowired
    public GradeService(HrsGradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    /**
     * Retrieve all grades (cached under "grades").
     */
    @Async
    @Cacheable(cacheNames = "grades")
    public CompletableFuture<List<HrsGrade>> getAllGrades() {
        List<HrsGrade> all = gradeRepository.findAll();
        return CompletableFuture.completedFuture(all);
    }

    /**
     * Retrieve a single grade by ID (cached under "grade" with key = gradeId).
     */
    @Async
    @Cacheable(cacheNames = "grade", key = "#id", condition = "#id != null")
    public CompletableFuture<HrsGrade> getGradeById(Long id) {
        return CompletableFuture.supplyAsync(() ->
            gradeRepository.findById(id).orElse(null)
        );
    }

    /**
     * Create a new grade (evicts both "grades" and "grade" caches).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"grades", "grade"}, allEntries = true)
    public CompletableFuture<HrsGrade> createGrade(HrsGrade grade) {
        HrsGrade saved = gradeRepository.save(grade);
        return CompletableFuture.completedFuture(saved);
    }

    /**
     * Update an existing grade (evicts both "grades" and "grade" caches).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"grades", "grade"}, allEntries = true)
    public CompletableFuture<HrsGrade> updateGrade(Long id, HrsGrade updatedGrade) {
        return CompletableFuture.supplyAsync(() -> {
            HrsGrade existing = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found: " + id));
            existing.setGradeCode(updatedGrade.getGradeCode());
            existing.setGradeName(updatedGrade.getGradeName());
            return gradeRepository.save(existing);
        });
    }

    /**
     * Delete a grade by ID (evicts both "grades" and "grade" caches).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"grades", "grade"}, allEntries = true)
    public CompletableFuture<Void> deleteGrade(Long id) {
        return CompletableFuture.runAsync(() -> gradeRepository.deleteById(id));
    }
}
