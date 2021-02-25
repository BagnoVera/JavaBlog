package com.luxoft.JavaBlog.comment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class DefaultCommentsService implements CommentsService {
    @Autowired
    private CommentsRepo commentsRepo;
    @Autowired
    private CommentsConverter commentsConverter;

    public DefaultCommentsService() {
    }

    private void validateCommentsDTO(CommentsDto commentsDTO) throws UnauthorizedCommentException {
        if (isNull(commentsDTO)) {
            throw new UnauthorizedCommentException("Object user is null, deleting a comment prohibited");
        }
        if (isNull(commentsDTO.getCommentName()) || commentsDTO.getCommentName().isEmpty() ) {
            throw new UnauthorizedCommentException("Email is empty, deleting a comment prohibited");
        }
    }

    public void saveComment(CommentsDto commentsDto)  {
        //validateCommentsDTO(commentsDto);
        Comments savedComment = commentsConverter.fromCommentDtoToComment(commentsDto);
        commentsRepo.save(savedComment);
    }
    public void deleteUser(Integer commentId) {
        commentsRepo.deleteById(commentId);
    }
    /*public CommentsDto findByEmail(String email) {
        Comments comments = commentsRepo.findByEmail(email);
        if (comments != null) {
            return commentsConverter.fromCommentToCommentDto(comments);
        }
        return null;
    } */
    public List<CommentsDto> findAll() {
        return commentsRepo.findAll()
                .stream()
                .map(commentsConverter::fromCommentToCommentDto)
                .collect(Collectors.toList());
    }

}
