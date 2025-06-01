package com.mycompany.hrs.controller;

import com.mycompany.hrs.entity.HrsAssignment;
import com.mycompany.hrs.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;



@RestController
@RequestMapping("/api/assignments")
public class AssignmentRestController {

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentRestController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // GET /api/assignments
    @GetMapping
    public CompletableFuture<ResponseEntity<List<HrsAssignment>>> getAllAssignments() {
        return assignmentService.getAllAssignments()
                .thenApply(ResponseEntity::ok);
    }

    // GET /api/assignments/{id}
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsAssignment>> getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id)
                .thenApply(a -> a != null
                        ? ResponseEntity.ok(a)
                        : ResponseEntity.notFound().build());
    }

    // POST /api/assignments
    @PostMapping
    public CompletableFuture<ResponseEntity<HrsAssignment>> createAssignment(@RequestBody HrsAssignment a) {
        return assignmentService.createAssignment(a)
                .thenApply(saved -> ResponseEntity.ok(saved));
    }

    // PUT /api/assignments/{id}
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<HrsAssignment>> updateAssignment(
            @PathVariable Long id, @RequestBody HrsAssignment a) {
        return assignmentService.updateAssignment(id, a)
                .thenApply(updated -> ResponseEntity.ok(updated));
    }

    // DELETE /api/assignments/{id}
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteAssignment(@PathVariable Long id) {
        return assignmentService.deleteAssignment(id)
                .thenApply(v -> ResponseEntity.noContent().build());
    }
}
