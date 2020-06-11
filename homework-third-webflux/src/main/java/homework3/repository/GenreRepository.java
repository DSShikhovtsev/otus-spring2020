package homework3.repository;

import homework3.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
