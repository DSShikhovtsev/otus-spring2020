package hystrix.service.book;

import hystrix.domain.Author;
import hystrix.domain.Book;
import hystrix.domain.Genre;
import hystrix.repository.AuthorRepository;
import hystrix.repository.BookRepository;
import hystrix.repository.CommentRepository;
import hystrix.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceMongo implements BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceMongo(BookRepository bookRepository, CommentRepository commentRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBookById(String id) {
        commentRepository.deleteByBookId(id);
        bookRepository.deleteById(id);
    }

    @Override
    public void addAuthorToBook(Book book, String id) {
        authorRepository.findById(id).ifPresent(author -> book.getAuthors().add(author));
    }

    @Override
    public void addGenreToBook(Book book, String id) {
        genreRepository.findById(id).ifPresent(genre -> book.getGenres().add(genre));
    }

    @Override
    public void deleteAuthorFomBook(Book book, String id) {
        Author author = authorRepository.findById(id).orElse(null);
        book.getAuthors().remove(author);
    }

    @Override
    public void deleteGenreFromBook(Book book, String id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        book.getGenres().remove(genre);
    }
}
