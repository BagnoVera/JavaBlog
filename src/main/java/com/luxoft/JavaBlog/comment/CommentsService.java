package com.luxoft.JavaBlog.comment;

import java.util.List;

public interface CommentsService {
    CommentsDto saveComment(CommentsDto commentsDto) throws UnauthorizedCommentException;

    /*void deleteComment(Integer commentId);

    CommentsDto findByEmail(String commentEmail); */

    List<CommentsDto> findAll();
}
