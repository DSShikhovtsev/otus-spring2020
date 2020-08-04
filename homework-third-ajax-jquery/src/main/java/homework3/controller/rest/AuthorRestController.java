package homework3.controller.rest;

import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorRestController {

    private final AuthorService service;

    public AuthorRestController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/api/authors")
    public List<Author> showAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/api/authors/{id}")
    public Author showAuthor(@PathVariable("id") String id) {
        return service.getAuthorById(id);
    }

    @PostMapping("/api/authors")
    public void saveAuthor(@RequestBody Author author) {
        service.save(author);
    }

    @DeleteMapping("/api/authors/delete")
    public void deleteAuthor(@RequestBody Author author) {
        service.deleteAuthor(author);
    }
}
