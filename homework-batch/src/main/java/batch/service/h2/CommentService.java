package batch.service.h2;


import batch.domain.h2.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Long bookId, Comment comment);
    void deleteComment(Long commentId);
    List<Comment> findAll();
    Comment getById(Long id);
}
