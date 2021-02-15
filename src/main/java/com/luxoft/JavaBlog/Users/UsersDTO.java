package com.luxoft.JavaBlog.Users;

import lombok.Builder;
import lombok.Data;

@Data
public class UsersDTO {
    private Integer id;
    private String name;
    private String passwd;
    private String email;

}


