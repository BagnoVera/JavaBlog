package com.luxoft.JavaBlog.Users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class DefaultUsersService implements UsersService{
    private final UsersRepo usersRepo;
    private final UsersConverter usersConverter;

    private void validateUserDTO(UsersDTO usersDTO) throws ValidationException {
        if (isNull(usersDTO)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDTO.getEmail()) || usersDTO.getEmail().isEmpty()) {
            throw new ValidationException("Email is empty");
        }
    }

    public UsersDTO saveUser(UsersDTO usersDto) throws ValidationException {
        validateUserDTO(usersDto);
        Users savedUser = usersRepo.save(usersConverter.fromUserDtoToUser(usersDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }
    public void deleteUser(Integer userId) {
        usersRepo.deleteById(userId);
    }
    public UsersDTO findByEmail(String email) {
        Users users = usersRepo.findByEmail(email);
        if (users != null) {
            return usersConverter.fromUserToUserDto(users);
        }
        return null;
    }
    public List<UsersDTO> findAll() {
        return usersRepo.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }



}
