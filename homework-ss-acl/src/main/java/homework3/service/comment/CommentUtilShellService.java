package homework3.service.comment;

import homework3.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentUtilShellService implements CommentUtilService {

    private CommentService commentService;

    public CommentUtilShellService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Transactional
    @Override
    public void addComment(Long bookId, Comment comment) {
        commentService.addComment(bookId, comment);
    }

    @Transactional
    @Override
    public void deleteComment(Comment comment) {
        commentService.deleteComment(comment);
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
