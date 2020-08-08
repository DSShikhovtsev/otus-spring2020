package hystrix.repository;

import hystrix.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

    void deleteByBookId(String bookId);
}
