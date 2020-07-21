package batch.reposinories.h2;

import batch.domain.h2.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class BookH2RepositoryImpl implements BookH2RepositoryCustom {

    private final BookH2Repository bookH2Repository;
    private final AuthorH2Repository authorH2Repository;
    private final GenreH2Repository genreH2Repository;

    @Lazy
    @Autowired
    public BookH2RepositoryImpl(BookH2Repository bookH2Repository, AuthorH2Repository authorH2Repository, GenreH2Repository genreH2Repository) {
        this.bookH2Repository = bookH2Repository;
        this.authorH2Repository = authorH2Repository;
        this.genreH2Repository = genreH2Repository;
    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book book = getById(bookId);
        book.getAuthors().add(authorH2Repository.findById(authorId).orElse(null));
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
        book.getGenres().add(genreH2Repository.findById(genreId).get());
        save(book);
    }

    @Override
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().removeIf(t -> t.getId().equals(genreId));
        save(book);
    }

    private void save(Book book) {
        bookH2Repository.save(book);
    }

    private Book getById(Long id) {
        return bookH2Repository.findById(id).get();
    }
}
