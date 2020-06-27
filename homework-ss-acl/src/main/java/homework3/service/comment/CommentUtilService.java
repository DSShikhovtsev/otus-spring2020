package homework3.service.comment;

import homework3.domain.Comment;

import java.util.List;

public interface CommentUtilService {

    void addComment(Long bookId, Comment comment);
    void deleteComment(Comment comment);
    List<Comment> findAll();
    Comment getById(Long id);
}
