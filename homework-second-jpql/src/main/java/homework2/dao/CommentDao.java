package homework2.dao;

import homework2.domain.Comment;

import java.util.List;

public interface CommentDao {

    void addComment(Long bookId, Comment comment);
    void deleteComment(Long commentId);
    List<Comment> findAll();
    Comment getById(Long id);
}
