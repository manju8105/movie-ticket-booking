package com.ty.jwtauthservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }

        final String jwt=authHeader.substring(7).trim();

        final String username=jwtUtil.extractEmail(jwt);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(username!=null && authentication==null)
        {
            UserDetails userDetails =userDetailsService.loadUserByUsername(username);

            if(jwtUtil.isTokenValid(jwt,userDetails)){
                List<String> roles=jwtUtil.extractRoles(jwt);

                List<GrantedAuthority> authorities=roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableList());


                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,authorities);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        filterChain.doFilter(request,response);
    }
}
