package batch.service.h2;

import batch.domain.h2.Comment;
import batch.reposinories.h2.CommentH2Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceJdbc implements CommentService {

    private final CommentH2Repository repository;
    private final BookService bookService;

    public CommentServiceJdbc(CommentH2Repository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void addComment(Long bookId, Comment comment) {
        comment.setBook(bookService.getBookById(bookId));
        repository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        repository.deleteById(commentId);
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
}
