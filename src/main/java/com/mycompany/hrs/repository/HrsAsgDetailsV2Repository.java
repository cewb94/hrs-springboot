/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.repository;


import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrsAsgDetailsV2Repository extends JpaRepository<HrsAsgDetailsV2, Long> {
    // Because this is a read-only view, we only provide findAll() / findById() etc.
}