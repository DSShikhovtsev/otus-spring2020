package homework2.service;

import homework2.dao.BookDao;
import homework2.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceJdbc implements BookService {

    private final BookDao bookDao;

    public BookServiceJdbc(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void putNewBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void addAuthorByBookId(Long bookId, Long authorId) {
        bookDao.addAuthorByBookId(bookId, authorId);
    }

    @Override
    public void deleteAuthorByBookId(Long bookId, Long authorId) {
        bookDao.deleteAuthorByBookId(bookId, authorId);
    }

    @Override
    public void addGenreByBookId(Long bookId, Long genreId) {
        bookDao.addGenreByBookId(bookId, genreId);
    }

    @Override
    public void deleteGenreByBookId(Long bookId, Long genreId) {
        bookDao.deleteGenreByBookId(bookId, genreId);
    }
}
