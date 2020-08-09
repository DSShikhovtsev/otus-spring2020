package actuator.service.comment;

import actuator.domain.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(String bookId, Comment comment);
    void deleteComment(String commentId);
    List<Comment> getAllComments();
    Comment getById(String id);
}