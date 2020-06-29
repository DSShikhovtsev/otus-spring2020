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
    public String showAuthors(Model model) {
        model.addAttribute("authors", service.getAllAuthors());
        return "authorsPage";
    }

    @GetMapping("/author")
    public String showAuthor(@RequestParam("id") Long id, Model model) {
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
        service.save(author);
        return "redirect:/showAuthors";
    }

    @PostMapping("/authorDelete")
    public String deleteAuthor(Author author) {
        service.deleteAuthor(author);
        return "redirect:/showAuthors";
    }
}
