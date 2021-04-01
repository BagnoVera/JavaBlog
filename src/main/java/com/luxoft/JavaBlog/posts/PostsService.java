package com.luxoft.JavaBlog.posts;

import java.io.File;
import java.util.List;

public interface PostsService {
    void savePost(PostsDto commentsDto) ;

    /*void deleteComment(Integer commentId);

    CommentsDto findByEmail(String commentEmail); */
    List<PostsDto> findPost(String name);

    List<PostsDto> findAll();
    Posts openPost(Integer id);
    String getFile(String path);
    //List<PostsDto> searchPost(String path);

}
