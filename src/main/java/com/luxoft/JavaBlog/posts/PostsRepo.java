package com.luxoft.JavaBlog.posts;

import com.luxoft.JavaBlog.comment.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts, Integer> {
    //Comments findComments(String comment);
    //Comments findByEmail(String commentEmail);
}
