package homework2.service;

import homework2.dao.CommentDao;
import homework2.domain.Book;
import homework2.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceJdbc implements CommentService {

    private CommentDao dao;
    private BookService bookService;

    public CommentServiceJdbc(CommentDao dao, BookService bookService) {
        this.dao = dao;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void addComment(Long bookId, Comment comment) {
        comment.setBook(bookService.getBookById(bookId));
        dao.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        dao.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(Long id) {
        return dao.findById(id).orElse(null);
    }
}
