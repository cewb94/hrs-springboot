package com.mycompany.hrs.controller;


import com.mycompany.hrs.entity.HrsDepartment;
import com.mycompany.hrs.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<HrsDepartment>>> getAllDepartments() {
        return departmentService.getAllDepartments()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsDepartment>> getDepartmentById(@PathVariable("id") Long deptId) {
        return departmentService.getDepartmentById(deptId)
                .thenApply(d -> d != null
                        ? ResponseEntity.ok(d)
                        : ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<HrsDepartment>> createDepartment(@RequestBody HrsDepartment d) {
        return departmentService.createDepartment(d)
                .thenApply(saved -> ResponseEntity.ok(saved));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsDepartment>> updateDepartment(
            @PathVariable Long id, @RequestBody HrsDepartment d) {
        return departmentService.updateDepartment(id, d)
                .thenApply(updated -> ResponseEntity.ok(updated));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteDepartment(@PathVariable("id") Long id) {
        return departmentService.deleteDepartment(id)
                .thenApply(v -> ResponseEntity.noContent().build());
    }
}
