package homework3.repository;

import homework3.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Lazy
    @Autowired
    public BookRepositoryCustomImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book book = getById(bookId);
        book.getAuthors().add(authorRepository.findById(authorId).orElse(null));
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
        book.getGenres().add(genreRepository.findById(genreId).get());
        save(book);
    }

    @Override
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().removeIf(t -> t.getId().equals(genreId));
        save(book);
    }

    private void save(Book book) {
        bookRepository.save(book);
    }

    private Book getById(Long id) {
        return bookRepository.findById(id).get();
    }
}
