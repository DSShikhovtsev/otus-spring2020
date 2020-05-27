package homework2.dao;

import homework2.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class BookDaoImpl implements BookDaoCustom {

    private BookDao bookDao;
    private AuthorDao authorDao;
    private GenreDao genreDao;

    @Lazy
    @Autowired
    public BookDaoImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book book = getById(bookId);
        book.getAuthors().add(authorDao.findById(authorId).orElse(null));
        save(book);
    }

    @Override
    public void deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getById(bookId);
        book.getAuthors().removeIf(t -> t.getId().equals(authorId));
        save(book);
    }

    @Override
    public void addGenreToBook(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().add(genreDao.findById(genreId).get());
        save(book);
    }

    @Override
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().removeIf(t -> t.getId().equals(genreId));
        save(book);
    }

    private void save(Book book) {
        bookDao.save(book);
    }

    private Book getById(Long id) {
        return bookDao.findById(id).get();
    }
}
