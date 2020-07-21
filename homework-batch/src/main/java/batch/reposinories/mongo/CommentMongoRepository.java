package batch.reposinories.mongo;

import batch.domain.mongo.Book;
import batch.domain.mongo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentMongoRepository extends MongoRepository<Comment, Long> {

    Comment findById(String id);
    void deleteById(String id);
    void deleteByBook(Book book);
}
