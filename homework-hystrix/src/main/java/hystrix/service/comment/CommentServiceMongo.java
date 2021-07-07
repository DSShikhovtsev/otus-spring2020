package hystrix.service.comment;

import hystrix.domain.Comment;
import hystrix.repository.CommentRepository;
import hystrix.service.book.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceMongo implements CommentService {

    private CommentRepository repository;
    private BookService bookService;

    public CommentServiceMongo(CommentRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Comment addComment(String bookId, Comment comment) {
        comment.setBook(bookService.getBookById(bookId));
        return repository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(String commentId) {
        repository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(String id) {
        return repository.findById(id).orElse(null);
    }
}
