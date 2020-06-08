package homework2.service;

import homework2.dao.CommentDao;
import homework2.domain.Book;
import homework2.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceMongo implements CommentService {

    private CommentDao dao;
    private BookService bookService;

    public CommentServiceMongo(CommentDao dao, BookService bookService) {
        this.dao = dao;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void addComment(String bookId, Comment comment) {
        comment.setBook(bookService.getBookById(bookId));
        dao.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(String commentId) {
        dao.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(String id) {
        return dao.findById(id);
    }
}
