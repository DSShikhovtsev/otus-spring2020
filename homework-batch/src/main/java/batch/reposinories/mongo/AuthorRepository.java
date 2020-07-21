package batch.reposinories.mongo;

import batch.domain.mongo.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, Long> {

    Author findById(String id);
    void deleteById(String id);
}
