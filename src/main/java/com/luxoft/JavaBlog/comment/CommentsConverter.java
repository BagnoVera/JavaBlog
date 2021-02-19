package com.luxoft.JavaBlog.comment;

import com.luxoft.JavaBlog.comment.Comments;
import com.luxoft.JavaBlog.comment.CommentsDto;
import org.springframework.stereotype.Component;

@Component
public class CommentsConverter {

    public Comments fromCommentDtoToComment(CommentsDto commentsDTO) {
        Comments comments = new Comments();
        comments.setCommentId(commentsDTO.getCommentId());
        comments.setCommentEmail(commentsDTO.getCommentEmail());
        comments.setCommentText(commentsDTO.getCommentText());
        return comments;
    }
    public CommentsDto fromCommentToCommentDto(Comments comments) {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentId(comments.getCommentId());
        commentsDto.setCommentEmail(comments.getCommentEmail());
        commentsDto.setCommentText(comments.getCommentText());
        return commentsDto;
    }
}