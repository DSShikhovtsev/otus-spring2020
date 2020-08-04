package homework3.controller;

import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/showAuthors")
    public String showAuthors() {
        return "authorsPage";
    }

    @GetMapping("/author")
    public String showAuthor(@RequestParam("id") String id, Model model) {
        model.addAttribute("author", service.getAuthorById(id));
        return "author";
    }

    @GetMapping("/addAuthor")
    public String addAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }
}
