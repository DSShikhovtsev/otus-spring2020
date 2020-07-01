package batch.service.mongo;

import batch.domain.mongo.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentUtilShellService implements CommentUtilService {

    private final CommentService commentService;

    public CommentUtilShellService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Transactional
    @Override
    public void addComment(String bookId, Comment comment) {
        commentService.addComment(bookId, comment);
    }

    @Transactional
    @Override
    public void deleteComment(String commentId) {
        commentService.deleteComment(commentId);
    }

    @Transactional
    @Override
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @Transactional
    @Override
    public Comment getById(String id) {
        return commentService.getById(id);
    }
}
