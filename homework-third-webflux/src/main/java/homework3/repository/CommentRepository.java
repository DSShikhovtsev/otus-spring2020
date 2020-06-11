package homework3.repository;

import homework3.domain.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    void deleteByBookId(String bookId);
}
