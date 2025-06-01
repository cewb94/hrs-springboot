package com.mycompany.hrs.repository;

import com.mycompany.hrs.entity.HrsEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface HrsEmployeeRepository extends JpaRepository<HrsEmployee, Long> {

//    /**
//     * Find employee by ID and return the entity if present.
//     * If not present, return the result of invoking the given Supplier.
//     */
//    default HrsEmployee findByIdOrElse(Long id, Supplier<HrsEmployee> fallback) {
//        Optional<HrsEmployee> opt = findById(id);
//        return opt.orElseGet(fallback);
//    }
//
//    /**
//     * Find employee by ID and return the entity if present.
//     * If not present, throw EntityNotFoundException with a standard message.
//     */
//    default HrsEmployee findByIdOrThrow(Long id) {
//        return findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("HrsEmployee not found with id=" + id));
//    }

    //public Optional<HrsEmployee> findById(Long id);
}
