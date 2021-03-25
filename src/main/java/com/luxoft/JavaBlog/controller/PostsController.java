package com.luxoft.JavaBlog.controller;

import com.luxoft.JavaBlog.posts.Posts;
import com.luxoft.JavaBlog.posts.PostsDto;
import com.luxoft.JavaBlog.posts.PostsService;
import com.luxoft.JavaBlog.posts.UnauthorizedPostException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@Log
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/save")
    public void savePosts(@RequestParam ("postName") String name, @RequestParam ("postTitle") String title,
                          @RequestParam ("postText") String text, @RequestParam("file") MultipartFile image)
            throws UnauthorizedPostException {
        PostsDto postsdto = new PostsDto();
        postsdto.setPostName(name);
        postsdto.setPostTitle(title);
        postsdto.setPostText(text);
        try {
            postsdto.setPostImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Handling save posts: " + postsdto);
        postsService.savePost(postsdto);

    }
    @GetMapping("/findPost/{name}")
    public List<PostsDto> findPost(@PathVariable @RequestParam (value = "name", required = true) String name) {
        log.info("Handling find a posts request");
        return postsService.findPost(name);
    }

    @GetMapping("/findAll")
    public List<PostsDto> findAllPosts() {
        log.info("Handling find all posts request");
        return postsService.findAll();
    }

    @GetMapping("/article/{id}")
    public Posts openPost(@PathVariable @RequestBody Integer id) {
        log.info("Handling find all articles request");
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