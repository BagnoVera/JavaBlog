package com.luxoft.JavaBlog.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
    //Comments findComments(String comment);
    //Comments findByEmail(String commentEmail);
}
