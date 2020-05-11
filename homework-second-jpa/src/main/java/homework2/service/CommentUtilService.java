package homework2.service;

import homework2.domain.Comment;

import java.util.List;

public interface CommentUtilService {

    void addComment(Long bookId, Comment comment);
    void deleteComment(Long commentId);
    List<Comment> findAll();
    Comment getById(Long id);
}
