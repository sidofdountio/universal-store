package com.meche.security.auth;

import com.meche.security.model.Appuser;
import com.meche.security.model.AuthenticationRequest;
import com.meche.security.model.AuthenticationResponse;
import com.meche.security.model.RegisterRequest;
import com.meche.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 10/4/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("api/v1/hair/auth")
@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
public class Auth {
    private final UserService userService;
    private final LogoutHandler logoutHandler;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) throws InterruptedException {
        AuthenticationResponse registered = userService.register(registerRequest);
        return new ResponseEntity<AuthenticationResponse>(registered, CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws InterruptedException {
        AuthenticationResponse authenticate = userService.authenticate(authenticationRequest);
        TimeUnit.SECONDS.sleep(2);
        return new ResponseEntity<AuthenticationResponse>(authenticate, CREATED);
    }

    @GetMapping(path = "/isTokenValid/{token}")
    public ResponseEntity<Boolean> isTokenValid(@PathVariable("token") String token) {
        return new ResponseEntity<Boolean>(userService.isTokenValid(token), OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Appuser> isTokenValid(@PathVariable("id") Long id) {
        return new ResponseEntity<Appuser>(userService.getUser(id), OK);
    }
}
