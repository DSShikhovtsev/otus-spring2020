package batch.service.mongo;

import batch.reposinories.mongo.CommentMongoRepository;
import batch.domain.mongo.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceMongo implements CommentService {

    private final CommentMongoRepository repository;
    private final BookService bookService;

    public CommentServiceMongo(CommentMongoRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void addComment(String bookId, Comment comment) {
        comment.setBook(bookService.getBookById(bookId));
        repository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(String commentId) {
        repository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        repository.save(comment);
    }
}
