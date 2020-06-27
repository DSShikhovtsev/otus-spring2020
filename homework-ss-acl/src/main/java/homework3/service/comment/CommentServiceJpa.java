package homework3.service.comment;

import homework3.domain.Comment;
import homework3.repository.CommentRepository;
import homework3.service.book.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceJpa implements CommentService {

    private CommentRepository repository;
    private BookService bookService;

    public CommentServiceJpa(CommentRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Comment addComment(Long bookId, Comment comment) {
        comment.setBook(bookService.getBookById(bookId));
        return repository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Comment comment) {
        repository.deleteById(comment.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteByBookId(Long id) {
        repository.deleteByBookId(id);
    }
}
