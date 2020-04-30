package homework2.service;

import homework2.dao.BookDao;
import homework2.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceJdbc implements BookService {

    private final BookDao bookDao;

    public BookServiceJdbc(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(Long id) {
        return bookDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        bookDao.deleteById(id);
    }

    @Transactional
    @Override
    public void addGenreByBookId(Long bookId, Long genreId) {
        bookDao.addGenreByBookId(bookId, genreId);
    }

    @Transactional
    @Override
    public void deleteGenreByBookId(Long bookId, Long genreId) {
        bookDao.deleteGenreByBookId(bookId, genreId);
    }
}
