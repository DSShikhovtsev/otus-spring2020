package homework3.repository;

import homework3.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
