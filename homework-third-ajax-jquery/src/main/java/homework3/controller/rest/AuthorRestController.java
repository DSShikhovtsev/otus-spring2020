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

    @GetMapping("/api/showAuthors")
    public List<Author> showAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/api/author")
    public Author showAuthor(@RequestParam("id") String id) {
        return service.getAuthorById(id);
    }

    @PostMapping("/api/author")
    public void saveAuthor(@RequestBody Author author) {
        service.save(author);
    }
}
