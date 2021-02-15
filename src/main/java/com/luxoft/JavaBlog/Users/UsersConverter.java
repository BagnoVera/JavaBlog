package com.luxoft.JavaBlog.Users;

import org.springframework.stereotype.Component;

@Component
public class UsersConverter {
    public Users fromUserDtoToUser(UsersDTO usersDTO) {
        Users users = new Users();
        users.setId(usersDTO.getId());
        users.setEmail(usersDTO.getEmail());
        users.setName(usersDTO.getName());
        users.setPasswd(usersDTO.getPasswd());
        return users;
    }
    public UsersDTO fromUserToUserDto(Users users) {
        UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(users.getId());
            usersDTO.setEmail(users.getEmail());
            usersDTO.setName(users.getName());
            usersDTO.setPasswd(users.getPasswd());
        return usersDTO;
    }
}
