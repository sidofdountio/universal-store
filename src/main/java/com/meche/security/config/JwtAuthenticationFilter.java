package com.meche.security.config;

import com.meche.security.repo.TokenRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author sidof
 * @Since 10/3/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepo tokenRepo;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//    header
        String authHeader = request.getHeader("Authorization");
        String token;
        String userEmail;
//        Check if the head of request it's not null and started with Bearer
        if ((authHeader == null) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
//        remove 7 length for authHeader to get the token.
        token = authHeader.substring(7);
//       If ok extract email from the token
        userEmail = jwtService.extractUserEmail(token);
//        Check if user are already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            Fetching user in database.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            Check if token is valid and valid on database
            var isValidToken = tokenRepo.findByToken(token)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isValidToken(token, userDetails) && isValidToken) {
//                Create user: UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
//                force security
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
//                Update security context.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
//            Give hand for the next request
            filterChain.doFilter(request, response);
        }
    }
}
