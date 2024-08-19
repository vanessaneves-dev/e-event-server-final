package com.projetofinal.eeventserverfinal.security;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.projetofinal.eeventserverfinal.service.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //  SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            //  var subjectToken = this.jwtProvider.validateToken(header);
            String token = header.substring(7); // Remove o prefixo "Bearer "

            try {
                var subjectToken = this.jwtProvider.validateToken(token);
                if (subjectToken.isEmpty()) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    request.setAttribute("organizer_id", subjectToken);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            filterChain.doFilter(request, response);
        }
    }
}