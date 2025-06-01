package com.mycompany.hrs.controller;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.service.AsgDetailsV2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/emp_assi_dets_v2")
public class AsgDetailsV2RestController {

    private final AsgDetailsV2Service service;
    
    @Autowired
    public AsgDetailsV2RestController(AsgDetailsV2Service service) {
        this.service = service;
    }
    
    
    @GetMapping
    public CompletableFuture<ResponseEntity<List<HrsAsgDetailsV2>>> getAllAsgDetails() {
        return service.getAllAsgDetails().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsAsgDetailsV2>> getJobById(@PathVariable Long id) {
        return service.getAsgDetailById(id).thenApply(j -> j != null
                ? ResponseEntity.ok(j)
                : ResponseEntity.notFound().build());
    }

}