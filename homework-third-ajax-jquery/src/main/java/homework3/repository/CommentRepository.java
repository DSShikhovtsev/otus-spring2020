package homework3.repository;

import homework3.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

    void deleteByBookId(String bookId);
}
