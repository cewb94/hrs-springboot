package com.mycompany.hrs.repository;

import com.mycompany.hrs.entity.HrsEmployee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HrsEmployeeRepository extends JpaRepository<HrsEmployee, Long> {

}
