package com.luxoft.JavaBlog.controller;

import com.luxoft.JavaBlog.comment.CommentsDto;
import com.luxoft.JavaBlog.comment.CommentsService;
import com.luxoft.JavaBlog.comment.UnauthorizedCommentException;
import com.luxoft.JavaBlog.users.UsersDto;
import com.luxoft.JavaBlog.users.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@Log
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/save")
    public CommentsDto saveComments(@RequestBody CommentsDto CommentsDTO) throws UnauthorizedCommentException {
        log.info("Handling save comments: " + CommentsDTO);
        return commentsService.saveComment(CommentsDTO);
    }

    @GetMapping("/findAll")
    public List<CommentsDto> findAllComments() {
        log.info("Handling find all comments request");
        return commentsService.findAll();
    }

    /*@GetMapping("/findByEmail")
    public CommentsDto findByEmail(@RequestParam String email) {
        log.info("Handling find by email request: " + email);
        return commentsService.findByEmail(email);
    } */

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComments(@PathVariable Integer id) {
        log.info("Handling delete comment request: " + id);
        // commentsService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
