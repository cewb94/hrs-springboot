/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.controller;

import com.mycompany.hrs.entity.HrsAsgDetailsV3;
import com.mycompany.hrs.service.AsgDetailsV3Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for SQL view hrs_asg_details_v3
 * @author Yaqoub Alshatti
 */
@Controller
@RequestMapping("/api/asg_dets_v3")
public class AsgDetailsV3RestController {

    private final AsgDetailsV3Service service;
    
    @Autowired
    public AsgDetailsV3RestController(AsgDetailsV3Service service) {
        this.service = service;
    }
    
    
    @GetMapping
    public CompletableFuture<ResponseEntity<List<HrsAsgDetailsV3>>> getAllAsgDetails() {
        return service.getAllAsgDetails().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsAsgDetailsV3>> getAsgDetailById(@PathVariable("id") Long id) {
        return service.getAsgDetailById(id).thenApply(j -> j != null
                ? ResponseEntity.ok(j)
                : ResponseEntity.notFound().build());
    }
    
    @GetMapping("/emp_num/{empNumber}")
    public CompletableFuture<ResponseEntity<HrsAsgDetailsV3>> getAsgDetailByEmpNum(@PathVariable("empNumber") String empNumber) {
        return service.getAsgDetailByEmpNum(empNumber).thenApply(j -> j != null
                ? ResponseEntity.ok(j)
                : ResponseEntity.notFound().build());
    }

}
