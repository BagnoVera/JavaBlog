package com.luxoft.JavaBlog.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class DefaultCommentsService implements CommentsService {
    private CommentsRepo commentsRepo;
    private CommentsConverter commentsConverter;

    public DefaultCommentsService() {
    }

    private void validateCommentsDTO(CommentsDto commentsDTO) throws UnauthorizedCommentException {
        if (isNull(commentsDTO)) {
            throw new UnauthorizedCommentException("Object user is null, deleting a comment prohibited");
        }
        if (isNull(commentsDTO.getCommentEmail()) || commentsDTO.getCommentEmail().isEmpty() ) {
            throw new UnauthorizedCommentException("Email is empty, deleting a comment prohibited");
        }
    }

    public CommentsDto saveComment(CommentsDto commentsDto) throws UnauthorizedCommentException {
        validateCommentsDTO(commentsDto);
        Comments savedComment = commentsRepo.save(commentsConverter.fromCommentDtoToComment(commentsDto));
        return commentsConverter.fromCommentToCommentDto(savedComment);
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
