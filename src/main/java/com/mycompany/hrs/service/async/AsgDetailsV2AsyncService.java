/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.service.async;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.repository.HrsAsgDetailsV2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This bean is responsible for *only* doing the @Async fetch from the database.
 * None of its methods are @Cacheable.  Each method returns a CompletableFuture<T>.
 */
@Service
public class AsgDetailsV2AsyncService {

    private final HrsAsgDetailsV2Repository asgDetailsRepo;

    @Autowired
    public AsgDetailsV2AsyncService(HrsAsgDetailsV2Repository asgDetailsRepo) {
        this.asgDetailsRepo = asgDetailsRepo;
    }

    /**
     * Asynchronously load all HrsAsgDetailsV2 rows.
     */
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<HrsAsgDetailsV2>> fetchAllAsgDetails() {
        List<HrsAsgDetailsV2> all = asgDetailsRepo.findAll();
        return CompletableFuture.completedFuture(all);
    }

    /**
     * Asynchronously load one HrsAsgDetailsV2 by its ID.
     */
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<HrsAsgDetailsV2> fetchAsgDetailById(Long id) {
        HrsAsgDetailsV2 result = asgDetailsRepo.findById(id).orElse(null);
        return CompletableFuture.completedFuture(result);
    }

    /**
     * Asynchronously load one HrsAsgDetailsV2 by its employee number.
     */
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<HrsAsgDetailsV2> fetchAsgDetailByEmpNum(String empNumber) {
        HrsAsgDetailsV2 result = asgDetailsRepo.findByEmpNumber(empNumber);
        return CompletableFuture.completedFuture(result);
    }
}
