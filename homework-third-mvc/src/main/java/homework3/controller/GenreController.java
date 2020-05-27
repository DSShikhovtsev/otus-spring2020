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

    @GetMapping("/showGenre")
    public String showGenres(Model model) {
        model.addAttribute("genres", service.getAllGenres());
        return "genresPage";
    }

    @GetMapping("/genre")
    public String showGenre(@RequestParam("id") String id, Model model) {
        model.addAttribute("genre", service.getGenreById(id));
        return "genre";
    }

    @GetMapping("/addGenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre";
    }

    @PostMapping("/genre")
    public String saveGenre(Genre genre, Model model) {
        if (genre.getId().isEmpty()) genre.setId(null);
        service.save(genre);
        model.addAttribute("genres", service.getAllGenres());
        return "genresPage";
    }

    @PostMapping("/genreDelete")
    public String deleteGenre(@RequestParam("id") String id, Model model) {
        service.deleteGenreById(id);
        model.addAttribute("genres", service.getAllGenres());
        return "genresPage";
    }
}