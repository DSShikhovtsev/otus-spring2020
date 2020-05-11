package homework2.service;

import homework2.dao.CommentDao;
import homework2.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceJdbc implements CommentService {

    private CommentDao dao;

    public CommentServiceJdbc(CommentDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void addComment(Long bookId, Comment comment) {
        dao.addComment(bookId, comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        dao.deleteComment(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(Long id) {
        return dao.getById(id);
    }
}
