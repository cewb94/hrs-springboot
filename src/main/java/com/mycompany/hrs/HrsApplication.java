/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author Yaqoub Alshatti
 */
@SpringBootApplication
@EnableCaching        // Enable @Cacheable, @CacheEvict, etc.
@EnableAsync          // Enable @Async on service methods
public class HrsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrsApplication.class, args);
    }
}
