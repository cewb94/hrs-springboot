package com.mycompany.hrs.controller;


import com.mycompany.hrs.entity.HrsJob;
import com.mycompany.hrs.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/jobs")
public class JobRestController {
    private final JobService jobService;
    @Autowired
    public JobRestController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<HrsJob>>> getAllJobs() {
        return jobService.getAllJobs().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsJob>> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id).thenApply(j -> j != null
                ? ResponseEntity.ok(j)
                : ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<HrsJob>> createJob(@RequestBody HrsJob j) {
        return jobService.createJob(j).thenApply(saved -> ResponseEntity.ok(saved));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsJob>> updateJob(
            @PathVariable Long id, @RequestBody HrsJob j) {
        return jobService.updateJob(id, j).thenApply(updated -> ResponseEntity.ok(updated));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id).thenApply(v -> ResponseEntity.noContent().build());
    }
}
