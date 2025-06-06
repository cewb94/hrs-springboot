package com.mycompany.hrs.controller;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.service.AsgDetailsV2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/asg_dets_v2")
public class AsgDetailsV2RestController {

    private final AsgDetailsV2Service service;

    @Autowired
    public AsgDetailsV2RestController(AsgDetailsV2Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HrsAsgDetailsV2>> getAllAsgDetails()
            throws InterruptedException, ExecutionException {
        List<HrsAsgDetailsV2> all = service.getAllAsgDetails();
        return ResponseEntity.ok(all);
    }
    // public CompletableFuture<ResponseEntity<List<HrsAsgDetailsV2>>>
    // getAllAsgDetails() throws ExecutionException, InterruptedException {
    // return service.getAllAsgDetails().thenApply(ResponseEntity::ok);
    // }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsAsgDetailsV2>> getAsgDetailById(@PathVariable("id") Long id)
            throws InterruptedException, ExecutionException {
        return service.getAsgDetailById(id).thenApply(j -> j != null
                ? ResponseEntity.ok(j)
                : ResponseEntity.notFound().build());
    }

    @GetMapping("/emp_num/{empNumber}")
    public CompletableFuture<ResponseEntity<HrsAsgDetailsV2>> getAsgDetailByEmpNum(
            @PathVariable("empNumber") String empNumber) throws InterruptedException, ExecutionException {
        return service.getAsgDetailByEmpNum(empNumber).thenApply(j -> j != null
                ? ResponseEntity.ok(j)
                : ResponseEntity.notFound().build());
    }

}