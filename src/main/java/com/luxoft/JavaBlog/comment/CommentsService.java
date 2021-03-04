package com.luxoft.JavaBlog.comment;

import java.util.List;

public interface CommentsService {
    void saveComment(CommentsDto commentsDto) ;
    List <CommentsDto> findAllComments(Integer postId);

    /*void deleteComment(Integer commentId);

    CommentsDto findByEmail(String commentEmail); */

    List<CommentsDto> findAll();
}
