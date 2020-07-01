package batch.service.h2;

import batch.domain.h2.Book;
import batch.reposinories.h2.BookH2Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceJdbc implements BookService {

    private final BookH2Repository repository;

    public BookServiceJdbc(BookH2Repository repository) {
        this.repository = repository;
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
    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        repository.addAuthorToBook(bookId, authorId);
    }

    @Transactional
    @Override
    public void deleteAuthorFromBook(Long bookId, Long authorId) {
        repository.deleteAuthorFromBook(bookId, authorId);
    }

    @Transactional
    @Override
    public void addGenreToBook(Long bookId, Long genreId) {
        repository.addGenreToBook(bookId, genreId);
    }

    @Transactional
    @Override
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        repository.deleteGenreFromBook(bookId, genreId);
    }
}
