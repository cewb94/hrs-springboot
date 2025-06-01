package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.repository.HrsAsgDetailsV2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsgDetailsV2Service {

    private final HrsAsgDetailsV2Repository asgDetailsRepo;

    @Autowired
    public AsgDetailsV2Service(HrsAsgDetailsV2Repository asgDetailsRepo) {
        this.asgDetailsRepo = asgDetailsRepo;
    }

    @Async
    @Cacheable(cacheNames = "asgDetailsV2")
    public CompletableFuture<List<HrsAsgDetailsV2>> getAllAsgDetails() {
        List<HrsAsgDetailsV2> all = asgDetailsRepo.findAll();
        return CompletableFuture.completedFuture(all);
    }

    @Async
    @Cacheable(cacheNames = "asgDetailsV2Item", key = "#id")
    public CompletableFuture<HrsAsgDetailsV2> getAsgDetailById(Long id) {
        return CompletableFuture.supplyAsync(() ->
                asgDetailsRepo.findById(id).orElse(null));
    }
}
