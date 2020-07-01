package batch.service.h2;

import batch.domain.h2.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentH2UtilShellService implements CommentH2UtilService {

    private final CommentService commentService;

    public CommentH2UtilShellService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Transactional
    @Override
    public void addComment(Long bookId, Comment comment) {
        commentService.addComment(bookId, comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        commentService.deleteComment(commentId);
    }

    @Transactional
    @Override
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @Transactional
    @Override
    public Comment getById(Long id) {
        return commentService.getById(id);
    }
}
