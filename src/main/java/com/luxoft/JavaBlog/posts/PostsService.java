package com.luxoft.JavaBlog.posts;

import java.util.List;

public interface PostsService {
    void savePost(PostsDto commentsDto) ;

    /*void deleteComment(Integer commentId);

    CommentsDto findByEmail(String commentEmail); */

    List<PostsDto> findAll();
    Posts openPost(Integer id);

}
