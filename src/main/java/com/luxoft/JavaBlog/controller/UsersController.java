package com.luxoft.JavaBlog.controller;

import com.luxoft.JavaBlog.comment.CommentsDto;
import com.luxoft.JavaBlog.comment.CommentsService;
import com.luxoft.JavaBlog.users.UsersDto;
import com.luxoft.JavaBlog.users.ValidationException;
import com.luxoft.JavaBlog.users.UsersService;

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
    public UsersDto saveUsers(@RequestBody UsersDto UsersDTO) throws ValidationException {
        log.info("Handling save users: " + UsersDTO);
        return usersService.saveUser(UsersDTO);
    }

    @GetMapping("/findAll")
    public List<UsersDto> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findByEmail")
    public UsersDto findByEmail(@RequestParam String email) {
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

