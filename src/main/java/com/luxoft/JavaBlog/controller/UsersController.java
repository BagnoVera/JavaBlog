package com.luxoft.JavaBlog.controller;

import com.luxoft.JavaBlog.Users.UsersDTO;
import com.luxoft.JavaBlog.Users.ValidationException;
import com.luxoft.JavaBlog.Users.UsersService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/save")
    public UsersDTO saveUsers(@RequestBody UsersDTO UsersDTO) throws ValidationException {
        log.info("Handling save users: " + UsersDTO);
        return usersService.saveUser(UsersDTO);
    }

    @GetMapping("/findAll")
    public List<UsersDTO> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findByEmail")
    public UsersDTO findByEmail(@RequestParam String email) {
        log.info("Handling find by email request: " + email);
        return usersService.findByEmail(email);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
