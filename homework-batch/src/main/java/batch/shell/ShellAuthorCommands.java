package batch.shell;

import batch.domain.mongo.Author;
import batch.service.mongo.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellAuthorCommands {

    private final AuthorService authorService;

    public ShellAuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "show authors", key = {"shA", "showAuthors"})
    public void showAuthors() {
        authorService.getAllAuthors().forEach(System.out::println);
    }

    @ShellMethod(value = "show author by id", key = {"sA", "showAuthor"})
    public void showAuthor(@ShellOption String id) {
        System.out.println(authorService.getAuthorById(id));
    }

    @ShellMethod(value = "add author", key = {"addA", "addAuthor"})
    public void addAuthor(@ShellOption String name) {
        authorService.save(new Author(name));
    }

    @ShellMethod(value = "delete author by id", key = {"delA", "delAuthor", "deleteAuthor"})
    public void deleteAuthor(@ShellOption String id) {
        authorService.deleteAuthorById(id);
    }

    @ShellMethod(value = "update Author name by id", key = {"updA", "updAuthor", "updateAuthor"})
    public void updateAuthor(@ShellOption String id, @ShellOption String name) {
        authorService.updateAuthor(new Author(id, name));
    }
}