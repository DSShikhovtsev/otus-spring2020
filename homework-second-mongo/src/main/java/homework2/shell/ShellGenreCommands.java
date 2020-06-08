package homework2.shell;

import homework2.domain.Genre;
import homework2.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellGenreCommands {

    private final GenreService genreService;

    public ShellGenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "show genres", key = {"shG", "showGenres"})
    public void showGenres() {
        genreService.getAllGenres().forEach(System.out::println);
    }

    @ShellMethod(value = "show genre", key = {"sG", "showGenre"})
    public void showGenre(@ShellOption String id) {
        System.out.println(genreService.getGenreById(id));
    }

    @ShellMethod(value = "add genre", key = {"addG", "addGenre"})
    public void addGenre(@ShellOption String description) {
        genreService.save(new Genre(description));
    }

    @ShellMethod(value = "delete genre by id", key = {"delG", "delGenre", "deleteGenre"})
    public void deleteGenre(@ShellOption String id) {
        genreService.deleteGenreById(id);
    }

    @ShellMethod(value = "update Genre description by id", key = {"updG", "updGenre", "updateGenre"})
    public void updateGenre(@ShellOption String id, @ShellOption String description) {
        genreService.updateGenre(new Genre(id, description));
    }
}
