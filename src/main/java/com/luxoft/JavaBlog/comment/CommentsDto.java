package com.luxoft.JavaBlog.comment;

import lombok.Data;

@Data
public class CommentsDto {
    private Integer commentId;
    private String commentName;
    private String commentText;
}
