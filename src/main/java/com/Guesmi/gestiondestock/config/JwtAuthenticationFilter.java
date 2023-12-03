package com.Guesmi.gestiondestock.config;


import com.Guesmi.gestiondestock.services.auth.ApplicationUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{


    private final JwtService jwtService ;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException  {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader= request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        final String idEntreprise ;
        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt= authHeader.substring(7);
        System.out.println(request.getHeaders("token"));

        System.out.println(jwt);
        String secretKey = request.getParameter("secret");
        String clientKey = request.getParameter("client");


        userEmail= jwtService.extractUsername(jwt);//todo extract the userEmail from JWT token
        idEntreprise = jwtService.extractIdEntreprise(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails =  this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt, userDetails)&& jwtService.isValidSecretAndClientKeys(secretKey, clientKey)) {
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        MDC.put("idEntreprise", idEntreprise);
        filterChain.doFilter(request, response);

    }




}
