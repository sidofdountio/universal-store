package com.meche.security.service;

import com.meche.security.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * @Author sidof
 * @Since 12/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutService implements LogoutHandler {
    private final TokenRepo tokenRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //    header
        String authHeader = request.getHeader("Authorization");
        String token;
//        Check if the head of request it's not null and started with Bearer
        if ((authHeader == null) || !authHeader.startsWith("Bearer ")) {
            return;
        }
        System.out.println("request.getHeader() = " + request.getHeader("Authorization"));
        token = authHeader.substring(7);
        var storedToken = tokenRepo.findByToken(token).orElse(null);
//        Check whether token ist null
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            log.info("Save expired the token");
            tokenRepo.save(storedToken);
        }
    }
}
