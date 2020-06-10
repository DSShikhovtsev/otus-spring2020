package homework3.service.comment;

import homework3.domain.Comment;

import java.util.List;

public interface CommentUtilService {

    void addComment(String bookId, Comment comment);
    void deleteComment(String commentId);
    List<Comment> findAll();
    Comment getById(String id);
}
