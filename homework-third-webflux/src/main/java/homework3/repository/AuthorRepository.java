package homework3.repository;

import homework3.domain.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
