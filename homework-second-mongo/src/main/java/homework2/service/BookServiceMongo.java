package homework2.service;

import homework2.dao.BookDao;
import homework2.dao.CommentDao;
import homework2.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceMongo implements BookService {

    private final BookDao bookDao;
    private final CommentDao commentDao;

    public BookServiceMongo(BookDao bookDao, CommentDao commentDao) {
        this.bookDao = bookDao;
        this.commentDao = commentDao;
    }

    @Transactional
    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(String id) {
        return bookDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        bookDao.save(book);
    }

    @Transactional
    @Override
    public void deleteBookById(String id) {
        commentDao.deleteByBook(getBookById(id));
        bookDao.deleteById(id);
    }
}
