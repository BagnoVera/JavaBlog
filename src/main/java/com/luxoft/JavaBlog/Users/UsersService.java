package com.luxoft.JavaBlog.Users;

import java.util.List;

public interface UsersService {
    UsersDTO saveUser(UsersDTO usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UsersDTO findByEmail(String email);

    List<UsersDTO> findAll();
}
