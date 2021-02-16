package com.luxoft.JavaBlog.comment;

public class UnauthorizedCommentException extends Exception{
    private String message;

    public UnauthorizedCommentException(String message) {
    }

    public String getMessage() {
        return message;
    }
}
