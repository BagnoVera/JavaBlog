package com.luxoft.JavaBlog.posts;

import lombok.Data;

@Data
public class PostsDto {
    private Integer postId;
    private String postName;
    private String postTitle;
    private String postText;
    private byte[] postImage;
}
