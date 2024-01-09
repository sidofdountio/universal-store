package com.meche.security.service;

import com.meche.security.config.JwtService;
import com.meche.security.model.*;
import com.meche.security.repo.TokenRepo;
import com.meche.security.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * @Author sidof
 * @Since 10/2/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService implements AppUserImplement {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepo tokenRepo;

    @Override
    public Appuser save(Appuser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("saving new user");
        return userRepo.save(user);
    }

    @Override
    public List<Appuser> getUsers() {
        log.info("Fetching users ...");
        return userRepo.findAll();
    }

    @Override
    public Appuser getUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }


    public AuthenticationResponse registerAdmin(RegisterRequest registerRequest) throws InterruptedException {
        var userToSave = Appuser.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .role(Role.ROLE_ADMIN)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        log.info("Register new user {}", userToSave);
//        save user
        var usersave = userRepo.save(userToSave);
//      generate token.
        String token = jwtService.generateToken(userToSave);
//        save token
        saveAppuserToken(usersave, token);
        return AuthenticationResponse.builder().token(token).build();
    }

    //Register user.
    public AuthenticationResponse register(RegisterRequest registerRequest) throws InterruptedException {
        var userToSave = Appuser.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .role(Role.ROLE_ADMIN)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        log.info("Register new user {}", userToSave);
//        save user
        var usersave = userRepo.save(userToSave);
//      generate token.
        String token = jwtService.generateToken(userToSave);
//        save token
        saveAppuserToken(usersave, token);
        return AuthenticationResponse.builder().token(token).build();
    }

    //    Authenticate method.
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("email or password not found."));
        String token = jwtService.generateToken(user);
//        revoke token
        revokeAppuserToken(user);
//        Save the generate token.
        saveAppuserToken(user, token);
        return AuthenticationResponse.builder().token(token).build();
    }

    private void saveAppuserToken(Appuser usersave, String token) {
        var tokeToSave = Token
                .builder()
                .appuser(usersave)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .token(token)
                .generateAt(now())
                .build();
        log.info(" save user token after login,logout or update: {}", token);
        tokenRepo.save(tokeToSave);
    }

    //    revoke all user token
    public void revokeAppuserToken(Appuser appuser) {
        var validUserToken = tokenRepo.findAllValidTokensByAppuser(appuser.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        log.info("user token had been revoked while login or logout : ");
        tokenRepo.saveAll(validUserToken);
    }

    public boolean isTokenValid(String token) {
        String username = jwtService.extractUserEmail(token);
        var user = userRepo.findByEmail(username).orElseThrow();

        var isValidToken = tokenRepo.findByToken(token)
                .map(t -> !t.isExpired() && !t.isRevoked())
                .orElse(false);
        log.info("Token is valid {}", token);
        return jwtService.isValidToken(token, user) && isValidToken;
    }

    @Override
    public Appuser edit(Appuser userToEdit) {
        var userToEditByEmail = userRepo.findByEmail(userToEdit.getEmail())
                .orElseThrow(() -> new IllegalStateException(String.format("user not exit %s", userToEdit.getEmail())));
        log.info("update current user {}", userToEdit);
//      update
        userToEdit.setPassword(passwordEncoder.encode(userToEdit.getPassword()));
        var editeUserSave = userRepo.save(userToEdit);
//        TODO: continue edite user properties
//        Revoke all previer tokens.
        revokeAppuserToken(userToEdit);
//        Generate new token.
        String token = jwtService.generateToken(userToEdit);
//        Save new generate token.
        saveAppuserToken(editeUserSave, token);

        return Appuser.builder()
                .email(editeUserSave.getEmail())
                .name(editeUserSave.getName())
                .role(editeUserSave.getRole())
                .tokens(editeUserSave.getTokens())
                .id(editeUserSave.getId())
                .build();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
