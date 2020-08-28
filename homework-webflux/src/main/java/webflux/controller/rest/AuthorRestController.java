package webflux.controller.rest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.domain.Author;
import webflux.service.author.AuthorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorRestController {

    private final AuthorService service;

    public AuthorRestController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/api/authors")
    public Flux<Author> showAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/api/authors/{id}")
    public Mono<Author> showAuthor(@PathVariable("id") String id) {
        return service.getAuthorById(id);
    }

    @PostMapping("/api/authors")
    public void saveAuthor(@RequestBody Author author) {
        service.save(author);
    }

    @DeleteMapping("/api/authors/{id}")
    public void deleteAuthor(@PathVariable("id") String id) {
        service.deleteAuthorById(id);
    }
}
