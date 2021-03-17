package com.luxoft.JavaBlog.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@AllArgsConstructor
@Service
public class DefaultUsersService implements UsersService{
    private final UsersRepo usersRepo;
    private final UsersConverter usersConverter;

    private static final String url = "jdbc:mysql://localhost:3306/javablog?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
    private static final String user = "root";
    private static final String password = "admin";


    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private void validateUserDTO(UsersDto usersDTO) throws ValidationException {
        if (isNull(usersDTO)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDTO.getEmail()) || usersDTO.getEmail().isEmpty()) {
            throw new ValidationException("Email is empty");
        }
    }

    public UsersDto save(UsersDto usersDto) throws ValidationException {
        validateUserDTO(usersDto);
        Users savedUser = usersRepo.save(usersConverter.fromUserDtoToUser(usersDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }

    public void deleteUser(Integer userId) {
        usersRepo.deleteById(userId);
    }


    public boolean login(String email, String passwd) {
        String query = "SELECT CASE when email=" +"'" + email + "'"+" and passwd=" + "'" + passwd + "'" +
                " then 1 else 0 end as ID from javablog.users where email='" + email + "';";
        String passwdSQL = "";
        Integer passwd1 = 0;
        System.out.println(query);
          try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                passwd1 = rs.getInt(1);
                System.out.println(passwd1);
                System.out.println("User logged in " + email + passwdSQL);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            if (passwd1 == 1){
                return true;
              }
            else{
                return false;
            }
        }
    }

    public List<UsersDto> findAll() {
        return usersRepo.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    public boolean findSearch(String email) {
        String query = "SELECT CASE when email='" + email +
                "' then 1 else 0 end as ID from javablog.users where email = '" + email +"';";
        Integer email1 = 0;
        System.out.println(query);
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                email1 = rs.getInt(1);
                System.out.println(email1);
                System.out.println("User searched in " + email);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            if (email1 == 1){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
