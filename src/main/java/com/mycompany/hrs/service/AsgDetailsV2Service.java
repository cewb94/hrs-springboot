package com.mycompany.hrs.service;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.service.async.AsgDetailsV2AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class AsgDetailsV2Service {

    private final AsgDetailsV2AsyncService asyncService;

    @Autowired
    public AsgDetailsV2Service(AsgDetailsV2AsyncService asyncService) {
        this.asyncService = asyncService;
    }


    @CachePut(cacheNames = "asgDetailsV2")
    public List<HrsAsgDetailsV2> getAllAsgDetails() throws InterruptedException, ExecutionException {
        // Kick off the async fetch; then .get() to wait until it’s done.
        CompletableFuture<List<HrsAsgDetailsV2>> future = asyncService.fetchAllAsgDetails();
        
        return future.get();
    }


    @CachePut(
        cacheNames = "asgDetailsV2Item",
        key = "#id",
        condition = "#id != null"
    )
    public CompletableFuture<HrsAsgDetailsV2> getAsgDetailById(Long id) throws InterruptedException, ExecutionException {
        CompletableFuture<HrsAsgDetailsV2> future = asyncService.fetchAsgDetailById(id);
        return future;
        //return future.get(); // wait for the async DB call to finish
    }


    @CachePut(
        cacheNames = "asgDetailsV2Item",
        key = "#empNumber",
        condition = "#empNumber != null"
    )
    public CompletableFuture<HrsAsgDetailsV2> getAsgDetailByEmpNum(String empNumber) throws InterruptedException, ExecutionException {
        CompletableFuture<HrsAsgDetailsV2> future = asyncService.fetchAsgDetailByEmpNum(empNumber);
        return future;
        //return future.get();
    }
    
    
    @CachePut(cacheNames = "pagedAsgDetailsV2")
    public Page<HrsAsgDetailsV2> getPagedAsgDetails(Specification<HrsAsgDetailsV2> spec, Pageable pg) throws InterruptedException, ExecutionException {

        return asyncService.getPagedAsgDetails(spec, pg);
    }
}
