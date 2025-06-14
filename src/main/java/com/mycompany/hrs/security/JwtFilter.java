package com.mycompany.hrs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Component
public class JwtFilter extends OncePerRequestFilter {

   private final JwtUtil jwtUtil;
   private final CustomUserDetailsService uds;

   @Autowired
   public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService uds) {
       this.jwtUtil = jwtUtil;
       this.uds = uds;
   }

   @Override
   protected void doFilterInternal(
           HttpServletRequest req,
           HttpServletResponse res,
           FilterChain chain
   ) throws ServletException, IOException {
       final String authHeader = req.getHeader("Authorization");
       String username = null;
       String jwt = null;

    //    System.out.println(authHeader);

       if (authHeader != null && authHeader.startsWith("Bearer ")) {
           jwt = authHeader.substring(7);
           try {
               username = jwtUtil.extractUsername(jwt);
           } catch (ExpiredJwtException e) {
                System.out.println(e);
               // optionally handle expired token
           }
       }

       // set authentication in security context to UsernamePasswordAuthenticationToken auth object
       if (username != null &&
           SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = uds.loadUserByUsername(username);
           if (jwtUtil.validateToken(jwt, userDetails)) {
               UsernamePasswordAuthenticationToken auth = 
                   new UsernamePasswordAuthenticationToken(
                     userDetails, null, userDetails.getAuthorities()
                   );
               auth.setDetails(
                 new WebAuthenticationDetailsSource().buildDetails(req)
               );
               System.out.println("set authentication in security context");
               SecurityContextHolder.getContext().setAuthentication(auth);
           }

       }

       System.out.println(SecurityContextHolder.getContext().getAuthentication());

       chain.doFilter(req, res);
   }
}
