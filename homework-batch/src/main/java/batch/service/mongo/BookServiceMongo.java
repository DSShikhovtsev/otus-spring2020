package batch.service.mongo;

import batch.reposinories.mongo.BookMongoRepository;
import batch.reposinories.mongo.CommentMongoRepository;
import batch.domain.mongo.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceMongo implements BookService {

    private final BookMongoRepository bookMongoRepository;
    private final CommentMongoRepository commentMongoRepository;

    public BookServiceMongo(BookMongoRepository bookMongoRepository, CommentMongoRepository commentMongoRepository) {
        this.bookMongoRepository = bookMongoRepository;
        this.commentMongoRepository = commentMongoRepository;
    }

    @Transactional
    @Override
    public void save(Book book) {
        bookMongoRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(String id) {
        return bookMongoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookMongoRepository.findAll();
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        bookMongoRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBookById(String id) {
        commentMongoRepository.deleteByBook(getBookById(id));
        bookMongoRepository.deleteById(id);
    }
}
