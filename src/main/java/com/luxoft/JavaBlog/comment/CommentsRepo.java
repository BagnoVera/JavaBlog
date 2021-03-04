package com.luxoft.JavaBlog.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
    //Comments findComments(String comment);
    //Comments findByEmail(String commentEmail);
    @Query("select comments from Comments comments where comments.commentPostId=:postId")
    List<Comments> findAllComments(@Param("postId") Integer idPost);
}
