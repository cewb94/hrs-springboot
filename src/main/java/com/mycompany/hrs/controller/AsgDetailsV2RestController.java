package com.mycompany.hrs.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.service.AsgDetailsV2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


     private static class Sorter { public String field, dir; }
    private static class Filter { public String field, type; public Object value; }

    @GetMapping("/table")
    public Map<String,Object> list(
        @RequestParam(defaultValue="1") int page,
        @RequestParam(defaultValue="10") int size,
        @RequestParam(required=false) String sorters,
        @RequestParam(required=false) String filters
    ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // ----- Parse sorters -----
        Sort sort = Sort.unsorted();
        if (sorters != null) {
            List<Sorter> sorterList = mapper.readValue(sorters, new TypeReference<>(){});
            List<Sort.Order> orders = new ArrayList<>();
            for (Sorter s : sorterList) {
                orders.add(new Sort.Order(
                    "asc".equalsIgnoreCase(s.dir) ? Sort.Direction.ASC : Sort.Direction.DESC,
                    s.field
                ));
            }
            sort = Sort.by(orders);
        }

        // ----- Build pageable -----
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        // ----- (Optional) Parse filters into a Specification ----- 
        Specification<HrsAsgDetailsV2> spec = Specification.where(null);
        if (filters != null) {
            List<Filter> filterList = mapper.readValue(filters, new TypeReference<>(){});
            for (Filter f : filterList) {
                spec = spec.and((root, q, cb) -> {
                    if ("like".equals(f.type)) {
                        return cb.like(cb.lower(root.get(f.field)), "%" + f.value.toString().toLowerCase() + "%");
                    } else {
                        return cb.equal(root.get(f.field), f.value);
                    }
                });
            }
        }

        // ----- Query -----
        Page<HrsAsgDetailsV2> pageData = service.getPagedAsgDetails(spec, pageable);

        // ----- Prepare response -----
        Map<String,Object> resp = new HashMap<>();
        resp.put("data", pageData.getContent());
        resp.put("last_page", pageData.getTotalPages());
        return resp;
    }


}