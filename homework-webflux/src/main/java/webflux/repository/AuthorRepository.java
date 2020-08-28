package webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import webflux.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<String> deleteAuthorById(String id);
}
