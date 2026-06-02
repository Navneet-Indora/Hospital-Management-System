package org.example.hospitalmanagement.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hospitalmanagement.security.entity.User;
import org.example.hospitalmanagement.security.repository.UserRepository;
import org.example.hospitalmanagement.security.service.JwtService;

import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        //step1 get Authorization header
        String authHeader=request.getHeader("Authorization");

        //step 2- check if header exists and start with bearer
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        //step-3 Extract token
        String token=authHeader.substring(7);

        //step4- extract email from token
        String email=jwtService.extractEmail(token);
        //step 5 Validate token
        if(email != null &&
                SecurityContextHolder.getContext().getAuthentication()==null){
            User user=userRepository.findByEmail(email).orElse(null);
            if(user!= null && jwtService.validateToken(token,user)){
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                //here SecurityContextHolder is filled by filter
            }
        }
        //to move ahead in filter chain
        filterChain.doFilter(request,response);
    }
}
