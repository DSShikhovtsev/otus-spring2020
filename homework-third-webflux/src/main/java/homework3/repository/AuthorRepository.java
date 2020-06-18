package homework3.repository;

import homework3.domain.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Flux<Author> findAll();

    Mono<Author> findById(String id);

    Mono<Void> save(Author author);
}
