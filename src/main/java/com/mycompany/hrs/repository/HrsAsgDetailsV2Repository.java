/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.repository;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

//import org.springframework.data.jpa.repository.Query;

@Repository
public interface HrsAsgDetailsV2Repository
        extends JpaRepository<HrsAsgDetailsV2, Long>, JpaSpecificationExecutor<HrsAsgDetailsV2> {

    // Because this is a read-only view, we only provide findAll() / findById() etc.

    public HrsAsgDetailsV2 findByEmpNumber(String empNumber);

}