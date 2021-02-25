package com.luxoft.JavaBlog.posts;

import com.luxoft.JavaBlog.posts.Posts;
import com.luxoft.JavaBlog.posts.PostsConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
