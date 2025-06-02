/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.repository;

import com.mycompany.hrs.entity.HrsAsgDetailsV3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Yaqoub Alshatti
 */
@Repository
public interface HrsAsgDetailsV3Repository extends JpaRepository<HrsAsgDetailsV3, Long> {

    @NativeQuery("select asg.* from hrs.hrs_asg_details_v3 asg where asg.emp_number = ?1")
    public HrsAsgDetailsV3 findByEmpNumber(String empNumber);
}
