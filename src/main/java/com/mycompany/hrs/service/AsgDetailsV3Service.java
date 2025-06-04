/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsAsgDetailsV3;
import com.mycompany.hrs.repository.HrsAsgDetailsV3Repository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yaqoub Alshatti
 */
@Service
public class AsgDetailsV3Service {
    
    private final HrsAsgDetailsV3Repository asgDetailsRepo;

    @Autowired
    public AsgDetailsV3Service(HrsAsgDetailsV3Repository asgDetailsRepo) {
        this.asgDetailsRepo = asgDetailsRepo;
    }
    
    @Async
    @CachePut(cacheNames = "asgDetailsV3")
    public CompletableFuture<List<HrsAsgDetailsV3>> getAllAsgDetails() {
        List<HrsAsgDetailsV3> all = asgDetailsRepo.findAll();
        return CompletableFuture.completedFuture(all);
    }

    @Async
    @CachePut(cacheNames = "asgDetailsV3Item", key = "#id", condition = "#id != null")
    public CompletableFuture<HrsAsgDetailsV3> getAsgDetailById(Long id) {
        return CompletableFuture.supplyAsync(() ->
                asgDetailsRepo.findById(id).orElse(null));
    }
    
    
    @Async
    @CachePut(cacheNames = "asgDetailsV3EmpNum", key = "#empNumber", condition = "#empNumber != null")
    public CompletableFuture<HrsAsgDetailsV3> getAsgDetailByEmpNum(String empNumber) {
        return CompletableFuture.supplyAsync(() ->
                asgDetailsRepo.findByEmpNumber(empNumber));
    }
    
    
}
