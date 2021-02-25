package com.luxoft.JavaBlog.posts;

import com.luxoft.JavaBlog.posts.PostsDto;
import org.springframework.stereotype.Component;

@Component
public class PostsConverter {

    public Posts fromPostDtoToPost(PostsDto postsDTO) {
        Posts posts = new Posts();
        posts.setPostId(postsDTO.getPostId());
        posts.setPostName(postsDTO.getPostName());
        posts.setPostTitle(postsDTO.getPostTitle());
        posts.setPostText(postsDTO.getPostText());
        return posts;
    }
    public PostsDto fromPostToPostDto(Posts posts) {
        PostsDto postsDto = new PostsDto();
        postsDto.setPostId(posts.getPostId());
        postsDto.setPostName(posts.getPostName());
        postsDto.setPostTitle(posts.getPostTitle());
        postsDto.setPostText(posts.getPostText());
        return postsDto;
    }
}