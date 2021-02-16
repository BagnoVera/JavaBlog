package com.luxoft.JavaBlog.users;

import org.springframework.stereotype.Component;

@Component
public class UsersConverter {
    public Users fromUserDtoToUser(UsersDto usersDTO) {
        Users users = new Users();
        users.setId(usersDTO.getId());
        users.setEmail(usersDTO.getEmail());
        users.setName(usersDTO.getName());
        users.setPasswd(usersDTO.getPasswd());
        return users;
    }
    public UsersDto fromUserToUserDto(Users users) {
        UsersDto usersDto = new UsersDto();
            usersDto.setId(users.getId());
            usersDto.setEmail(users.getEmail());
            usersDto.setName(users.getName());
            usersDto.setPasswd(users.getPasswd());
        return usersDto;
    }
}
