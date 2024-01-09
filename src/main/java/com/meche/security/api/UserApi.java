package com.meche.security.api;

import com.meche.security.model.Appuser;
import com.meche.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 10/3/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("api/v1/hair/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80"},maxAge = 3600)
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;


    @PutMapping("/edit")
    public ResponseEntity<Appuser> updateUser(@RequestBody Appuser userToUpdate) {
        Appuser appuser = userService.edit(userToUpdate);
        return new ResponseEntity(appuser, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Appuser>> defaultUsers() {
        List<Appuser> users = userService.getUsers();

        return new ResponseEntity<>(users, OK);
    }

}

