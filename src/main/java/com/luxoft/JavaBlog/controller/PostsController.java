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
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/findAll")
    public List<PostsDto> findAllPosts() {
        log.info("Handling find all posts request");
        return postsService.findAll();
    }

    @GetMapping("/findPost/{name}")
    public List<PostsDto> findPost(@PathVariable @RequestBody String name) {
        log.info("Handling find a posts request");
        return postsService.findPost(name);
    }

    @GetMapping("/article/{id}")
    public Posts openPost(@PathVariable @RequestBody Integer id) {
        log.info("Handling find all articles request");
        return postsService.openPost(id);
    }

    @GetMapping("/path/**")
    public String getFile(/*@PathVariable @RequestBody String name,*/ HttpServletRequest request) {
        String restOfTheUrl = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        log.info("Handling PathTraversal");
        log.info(restOfTheUrl);
        String pathToFile = restOfTheUrl.substring(12,restOfTheUrl.length());
        log.info(pathToFile);
        return postsService.getFile(pathToFile);
    }

    /*@GetMapping("/blog/files")
    public String downloadFile(@RequestParam(value = "f") String fileName) throws IOException {
        Path source = Paths.get(folder,"\\", fileName);
        Path dest = Paths.get(downloads, "\\", "copy.txt");
        fileService.copyFile(source, dest);
        return "redirect:/blog";
    }*/


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePosts(@PathVariable Integer id) {
        log.info("Handling delete post request: " + id);
        // postsService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    /*@RequestMapping(value = "/searchPost/{path}", method = RequestMethod.GET)
    public String searchPost(@RequestParam (value="path", required=false) String path) {
        log.info("Trying to read file" + path);
        return postsService.getFile(path);
    }*/

}