package com.luxoft.JavaBlog.posts;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class DefaultPostsService implements PostsService {
    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private PostsConverter postsConverter;


    private static final String url = "jdbc:mysql://localhost:3306/javablog?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
    private static final String user = "root";
    private static final String password = "admin";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public DefaultPostsService() {
    }

    private void validatePostsDTO(PostsDto postsDTO) throws UnauthorizedPostException {
        if (isNull(postsDTO)) {
            throw new UnauthorizedPostException("Object user is null, deleting a post prohibited");
        }
        if (isNull(postsDTO.getPostName()) || postsDTO.getPostName().isEmpty() ) {
            throw new UnauthorizedPostException("Email is empty, deleting a post prohibited");
        }
    }

    public void savePost(PostsDto postsDto)  {
        //validatePostsDTO(postsDto);
        Posts savedPost = postsConverter.fromPostDtoToPost(postsDto);
        postsRepo.save(savedPost);
    }
    public Posts openPost(Integer id){
        Posts openPost = new Posts();
        openPost = postsRepo.findById(id).orElseThrow(() -> new UnauthorizedPostException("String"));
        return openPost;
    }

    public List<PostsDto> findPost(String name){
        String query = "SELECT * FROM javablog.post where post_title = '" + name + "';";

        String title1 = "";
        String name1 = "";
        String text1 = "";
        Integer id1 = 0;
        List<PostsDto> newFindPost = new ArrayList<>();
        System.out.println(query);
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                PostsDto findPostNow = new PostsDto();
                title1 = rs.getString("post_title");
                name1 = rs.getString("post_name");
                text1 = rs.getString("post_text");
                id1 = rs.getInt("post_id");

                findPostNow.setPostTitle(title1);
                findPostNow.setPostName(name1);
                findPostNow.setPostText(text1);
                findPostNow.setPostId(id1);

                newFindPost.add(findPostNow);

                System.out.println(title1);
                System.out.println("Post found " + name);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            if (!newFindPost.isEmpty()){
                return newFindPost;
            }
            else{
                System.out.println("Такого поста не существует!");
                return newFindPost;
            }

        }

    }

    public void deletePost(Integer postId) {
        postsRepo.deleteById(postId);
    }

    /*public PostsDto findByPostId(Integer postId) {
        Posts posts = postsRepo.findById(postId);
        if (posts != null) {
            return postsConverter.fromPostToPostDto(posts);
        }
        return null;
    }*/

    public List<PostsDto> findAll() {
        return postsRepo.findAll()
                .stream()
                .map(postsConverter::fromPostToPostDto)
                .collect(Collectors.toList());
    }

}
