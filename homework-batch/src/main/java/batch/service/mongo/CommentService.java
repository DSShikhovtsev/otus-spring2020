package batch.service.mongo;

import batch.domain.mongo.Comment;

import java.util.List;

public interface CommentService {

    void addComment(String bookId, Comment comment);
    void deleteComment(String commentId);
    List<Comment> findAll();
    Comment getById(String id);
    void save(Comment comment);
}
