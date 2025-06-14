/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.repository;

import com.mycompany.hrs.entity.HrsEmpAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrsEmpAbsenceRepository extends JpaRepository<HrsEmpAbsence, Long> { }