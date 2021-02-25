package com.luxoft.JavaBlog.posts;

public class UnauthorizedPostException extends RuntimeException{
    private String message;

    public UnauthorizedPostException(String message) {
        super(message);
    }

}
