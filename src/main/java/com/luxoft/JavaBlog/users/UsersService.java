package com.luxoft.JavaBlog.users;

import java.util.List;

public interface UsersService {
    UsersDto saveUser(UsersDto usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UsersDto findByEmail(String email);

    List<UsersDto> findAll();
}
