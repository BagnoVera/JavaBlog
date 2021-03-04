package com.luxoft.JavaBlog.comment;

import com.luxoft.JavaBlog.comment.Comments;
import com.luxoft.JavaBlog.comment.CommentsDto;
import org.springframework.stereotype.Component;

@Component
public class CommentsConverter {

    public Comments fromCommentDtoToComment(CommentsDto commentsDTO) {
        Comments comments = new Comments();
        comments.setCommentId(commentsDTO.getCommentId());
        comments.setCommentPostId(commentsDTO.getCommentPostId());
        comments.setCommentName(commentsDTO.getCommentName());
        comments.setCommentText(commentsDTO.getCommentText());
        comments.setCommentImage(commentsDTO.getCommentImage());
        return comments;
    }
    public CommentsDto fromCommentToCommentDto(Comments comments) {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentId(comments.getCommentId());
        commentsDto.setCommentPostId(comments.getCommentPostId());
        commentsDto.setCommentName(comments.getCommentName());
        commentsDto.setCommentText(comments.getCommentText());
        commentsDto.setCommentImage(comments.getCommentImage());
        return commentsDto;
    }
}