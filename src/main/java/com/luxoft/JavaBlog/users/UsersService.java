package com.luxoft.JavaBlog.users;

import java.util.List;

public interface UsersService {
    UsersDto save(UsersDto usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    boolean login(String email, String passwd);

    List<UsersDto> findAll();

    boolean findSearch(String email);
}
