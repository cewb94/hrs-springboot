package com.mycompany.hrs.repository;

import com.mycompany.hrs.entity.HrsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HrsUserRepository extends JpaRepository<HrsUser, Long> {

    /**
     * Find a user by their username.
     * Used by CustomUserDetailsService for authentication.
     */
    Optional<HrsUser> findByUserName(String userName);
    
}
