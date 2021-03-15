package com.luxoft.JavaBlog.controller;

import com.luxoft.JavaBlog.comment.CommentsDto;
import com.luxoft.JavaBlog.comment.CommentsService;
import com.luxoft.JavaBlog.users.Users;
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
    public UsersDto saveUsers(@RequestBody UsersDto usersDTO) throws ValidationException {
        log.info("Handling save users: " + usersDTO);
        return usersService.save(usersDTO);
    }

    @GetMapping("/findAll")
    public List<UsersDto> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findSearch")
    public boolean findSearch(@RequestParam String email) {
        log.info("Handling find a user request");
        return usersService.findSearch(email);
    }

    @PostMapping("/findByEmail")
    public boolean findByEmail(@RequestBody UsersDto usersDto) {
        log.info("Handling find by email request: " + usersDto.getEmail());
        return usersService.findByEmail(usersDto.getEmail(), usersDto.getPasswd());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


}

