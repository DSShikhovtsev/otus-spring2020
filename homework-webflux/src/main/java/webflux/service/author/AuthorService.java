package webflux.service.author;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.domain.Author;

public interface AuthorService {

    void save(Author author);
    Mono<Author> getAuthorById(String id);
    Flux<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthorById(String id);
    void deleteAuthor(Author author);
}
