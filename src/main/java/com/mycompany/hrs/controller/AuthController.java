package com.mycompany.hrs.controller;

import com.mycompany.hrs.entity.HrsUser;
import com.mycompany.hrs.entity.Role;
import com.mycompany.hrs.repository.HrsUserRepository;
import com.mycompany.hrs.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authMgr;
    private final JwtUtil jwtUtil;
    private final HrsUserRepository userRepo;
    private final PasswordEncoder pwEncoder;

    @Autowired
    public AuthController(
            AuthenticationManager authMgr,
            JwtUtil jwtUtil,
            HrsUserRepository userRepo,
            PasswordEncoder pwEncoder) {
        this.authMgr = authMgr;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.pwEncoder = pwEncoder;
    }

    record AuthRequest(String username, String password) {
    }

    record AuthResponse(String token) {
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest body) {
        try {
            authMgr.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.username(), body.password()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }

        // load your HrsUser
        HrsUser u = userRepo.findByUserName(body.username()).get();

        // build Spring Security UserDetails with real GrantedAuthority objects
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                // === map Role enums to SimpleGrantedAuthority ===
                .authorities(
                        u.getRoles().stream()
                                .map(r -> new SimpleGrantedAuthority("" + r.name()))
                                .collect(Collectors.toList()))
                .build();
        System.out.println(userDetails.toString());
        // generate token
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest body) {
        if (userRepo.findByUserName(body.username()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Username already exists"));
        }
        HrsUser u = new HrsUser();
        u.setUsername(body.username());
        u.setPassword(pwEncoder.encode(body.password()));
        u.setRoles(Set.of(Role.USER));
        userRepo.save(u);
        return ResponseEntity.ok(Map.of("status", "registered"));
    }
}
