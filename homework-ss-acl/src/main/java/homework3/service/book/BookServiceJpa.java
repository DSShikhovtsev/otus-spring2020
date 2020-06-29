package homework3.service.book;

import homework3.domain.Book;
import homework3.repository.BookRepository;
import homework3.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceJpa implements BookService {

    private final BookRepository repository;
    private final CommentRepository commentRepository;

    public BookServiceJpa(BookRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        repository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(Book book) {
        commentRepository.deleteByBookId(book.getId());
        repository.deleteById(book.getId());
    }

    @Transactional
    @Override
    public void addAuthorToBook(Book book, Long authorId) {
        repository.addAuthorToBook(book.getId(), authorId);
    }

    @Transactional
    @Override
    public void deleteAuthorFromBook(Book book, Long authorId) {
        repository.deleteAuthorFromBook(book.getId(), authorId);
    }

    @Transactional
    @Override
    public void addGenreToBook(Book book, Long genreId) {
        repository.addGenreToBook(book.getId(), genreId);
    }

    @Transactional
    @Override
    public void deleteGenreFromBook(Book book, Long genreId) {
        repository.deleteGenreFromBook(book.getId(), genreId);
    }
}
