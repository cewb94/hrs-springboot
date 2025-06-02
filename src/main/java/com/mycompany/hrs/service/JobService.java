package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsJob;
import com.mycompany.hrs.repository.HrsJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class JobService {

    private final HrsJobRepository jobRepository;

    @Autowired
    public JobService(HrsJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Retrieve all jobs (cached under "jobs").
     */
    @Async
    @Cacheable(cacheNames = "jobs")
    public CompletableFuture<List<HrsJob>> getAllJobs() {
        List<HrsJob> all = jobRepository.findAll();
        return CompletableFuture.completedFuture(all);
    }

    /**
     * Retrieve a single job by ID (cached under "job" with key = jobId).
     */
    @Async
    @Cacheable(cacheNames = "job", key = "#id", condition = "#id != null")
    public CompletableFuture<HrsJob> getJobById(Long id) {
        return CompletableFuture.supplyAsync(() ->
            jobRepository.findById(id).orElse(null)
        );
    }

    /**
     * Create a new job (evicts both "jobs" and "job" caches).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"jobs", "job"}, allEntries = true)
    public CompletableFuture<HrsJob> createJob(HrsJob job) {
        HrsJob saved = jobRepository.save(job);
        return CompletableFuture.completedFuture(saved);
    }

    /**
     * Update an existing job (evicts both "jobs" and "job" caches).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"jobs", "job"}, allEntries = true)
    public CompletableFuture<HrsJob> updateJob(Long id, HrsJob updatedJob) {
        return CompletableFuture.supplyAsync(() -> {
            HrsJob existing = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found: " + id));
            existing.setJobCode(updatedJob.getJobCode());
            existing.setJobTitle(updatedJob.getJobTitle());
            return jobRepository.save(existing);
        });
    }

    /**
     * Delete a job by ID (evicts both "jobs" and "job" caches).
     */
    @Async
    @Transactional
    @CacheEvict(cacheNames = {"jobs", "job"}, allEntries = true)
    public CompletableFuture<Void> deleteJob(Long id) {
        return CompletableFuture.runAsync(() -> jobRepository.deleteById(id));
    }
}
