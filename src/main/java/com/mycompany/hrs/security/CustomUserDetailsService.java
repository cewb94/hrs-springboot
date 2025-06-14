package com.mycompany.hrs.security;

import com.mycompany.hrs.entity.HrsUser;
import com.mycompany.hrs.repository.HrsUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   private final HrsUserRepository userRepo;

   public CustomUserDetailsService(HrsUserRepository userRepo) {
       this.userRepo = userRepo;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       HrsUser u = userRepo.findByUserName(username)
               .orElseThrow(() ->
                   new UsernameNotFoundException("User not found: " + username));
       return new User(
           u.getUsername(),
           u.getPassword(),
           u.getRoles().stream()
             .map(r -> new SimpleGrantedAuthority(r.name()))
             .collect(Collectors.toSet())
       );
   }
}
