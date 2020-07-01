package batch.reposinories.mongo;

import batch.domain.mongo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookMongoRepository extends MongoRepository<Book, Long>, BookRepositoryCustom {

    Book findById(String id);
    void deleteById(String id);
}
