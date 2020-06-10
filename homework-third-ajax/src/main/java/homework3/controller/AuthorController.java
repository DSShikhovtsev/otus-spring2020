package homework3.controller;

import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/showAuthors")
    public List<Author> showAuthors(Model model) {
//        model.addAttribute("authors", service.getAllAuthors());
        return service.getAllAuthors();
    }

    @GetMapping("/author")
    public String showAuthor(@RequestParam("id") String id, Model model) {
        model.addAttribute("author", service.getAuthorById(id));
        return "author";
    }

    @GetMapping("/addAuthor")
    public String addAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "author";
    }

    @PostMapping("/author")
    public String saveAuthor(Author author) {
        if (author.getId().isEmpty()) author.setId(null);
        service.save(author);
        return "redirect:/showAuthors";
    }

    @PostMapping("/authorDelete")
    public String deleteAuthor(@RequestParam("id") String id) {
        service.deleteAuthorById(id);
        return "redirect:/showAuthors";
    }
}
