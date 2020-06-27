package homework3.controller;

import homework3.domain.Genre;
import homework3.service.genre.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping("/showGenres")
    public String showGenres(Model model) {
        model.addAttribute("genres", service.getAllGenres());
        return "genresPage";
    }

    @GetMapping("/genre")
    public String showGenre(@RequestParam("id") Long id, Model model) {
        model.addAttribute("genre", service.getGenreById(id));
        return "genre";
    }

    @GetMapping("/addGenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre";
    }

    @PostMapping("/genre")
    public String saveGenre(Genre genre) {
        service.save(genre);
        return "redirect:/showGenres";
    }

    @PostMapping("/genreDelete")
    public String deleteGenre(Genre genre) {
        service.deleteGenre(genre);
        return "redirect:/showGenres";
    }
}
