package batch.reposinories.mongo;

import batch.domain.mongo.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreMongoRepository extends MongoRepository<Genre, Long> {

    Genre findById(String id);
    void deleteById(String id);
}
