package com.projetofinal.eeventserverfinal.security;

import com.projetofinal.eeventserverfinal.service.JWTProvider;
import com.projetofinal.eeventserverfinal.service.JWTUserProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CombinedSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private JWTUserProvider jwtUserProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        // Ignorar a rota /api/user-event
        if (uri.startsWith("/api/user-event")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null) {
            var token = validateToken(header, request.getRequestURI());

            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            var roles = token.getClaim("roles").asList(String.class);
            var grants = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .toList();

            // Set attribute based on the route
            if (request.getRequestURI().startsWith("/api/organizer")) {
                request.setAttribute("organizer_id", token.getSubject());
            } else if (request.getRequestURI().startsWith("/api/user")) {
                request.setAttribute("user_id", token.getSubject());
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private com.auth0.jwt.interfaces.DecodedJWT validateToken(String header, String uri) {
        if (uri.startsWith("/api/organizer")) {
            return jwtProvider.validateToken(header);
        } else if (uri.startsWith("/api/user")) {
            return jwtUserProvider.validateToken(header);
        }
        return null;
    }
}
