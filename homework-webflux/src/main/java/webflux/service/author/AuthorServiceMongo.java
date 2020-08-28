package webflux.service.author;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.domain.Author;
import webflux.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceMongo implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceMongo(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void save(Author author) {
        authorRepository.save(author).block();
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void deleteAuthorById(String id) {
        authorRepository.deleteAuthorById(id).block();
    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }
}
