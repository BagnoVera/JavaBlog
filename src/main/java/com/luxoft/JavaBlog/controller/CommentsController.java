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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@Log
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/save")
    public void saveComments(@RequestParam ("commentName") String name, @RequestParam ("commentText") String text,
                             @RequestParam ("commentPostId") Integer id, @RequestParam("file") MultipartFile image) throws UnauthorizedCommentException {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentName(name);
        commentsDto.setCommentText(text);
        commentsDto.setCommentPostId(id);
        try {
            commentsDto.setCommentImage(image.getBytes());
            // commentsDto.setPostImageBase64(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Handling save posts: " + commentsDto);
        commentsService.saveComment(commentsDto);
    }

    @GetMapping("/findAll/{postId}")
    public List<CommentsDto> findAllComments(@PathVariable @RequestBody Integer postId) {
        log.info("Handling find all comments request");
        return commentsService.findAllComments(postId);
    }

    /*@GetMapping("/findByEmail")
    public CommentsDto findByEmail(@RequestParam String email) {
        log.info("Handling find by email request: " + email);
        return commentsService.findByEmail(email);
    } */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable Integer id) {
        log.info("Handling delete comment request: " + id);
        commentsService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
