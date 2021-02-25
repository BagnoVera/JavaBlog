package com.luxoft.JavaBlog.controller;

import com.luxoft.JavaBlog.posts.Posts;
import com.luxoft.JavaBlog.posts.PostsDto;
import com.luxoft.JavaBlog.posts.PostsService;
import com.luxoft.JavaBlog.posts.UnauthorizedPostException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@Log
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/save")
    public void savePosts(@RequestBody PostsDto postsDTO) throws UnauthorizedPostException {
        log.info("Handling save posts: " + postsDTO);
        postsService.savePost(postsDTO);
    }

    @GetMapping("/findAll")
    public List<PostsDto> findAllPosts() {
        log.info("Handling find all posts request");
        return postsService.findAll();
    }

    @GetMapping("/article/{id}")
    public Posts openPost(@PathVariable @RequestBody Integer id) {
        log.info("Handling find all posts request");
        return postsService.openPost(id);
    }
    /*@GetMapping("/findByPostId")
    public PostsDto findById(@RequestParam Integer postId) {
        log.info("Handling find by id request: " + postId);
        return postsService.findById(postId);
    } */

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePosts(@PathVariable Integer id) {
        log.info("Handling delete post request: " + id);
        // postsService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
