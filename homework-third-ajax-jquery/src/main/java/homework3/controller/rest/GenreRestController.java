package homework3.controller.rest;

import homework3.domain.Genre;
import homework3.service.genre.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreRestController {

    private final GenreService service;

    public GenreRestController(GenreService service) {
        this.service = service;
    }

    @GetMapping("/api/showGenres")
    public List<Genre> showGenres() {
        return service.getAllGenres();
    }

    @GetMapping("/api/genre")
    public Genre showGenre(@RequestParam("id") String id) {
        return service.getGenreById(id);
    }

    @PostMapping("/api/genre")
    public void saveGenre(@RequestBody Genre genre) {
        service.save(genre);
    }
}
