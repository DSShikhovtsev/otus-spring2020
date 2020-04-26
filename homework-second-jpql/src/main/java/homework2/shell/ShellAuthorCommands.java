package homework2.shell;

import homework2.domain.Author;
import homework2.service.AuthorService;
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
    public void showAuthor(@ShellOption(defaultValue = "1") Long id) {
        System.out.println(authorService.getAuthorById(id));
    }

    @ShellMethod(value = "add author", key = {"addA", "addAuthor"})
    public void addAuthor(@ShellOption String name) {
        authorService.save(new Author(name));
    }

    @ShellMethod(value = "delete author by id", key = {"delA", "delAuthor", "deleteAuthor"})
    public void deleteAuthor(@ShellOption Long id) {
        authorService.deleteAuthorById(id);
    }

    @ShellMethod(value = "update Author name by id", key = {"updA", "updAuthor", "updateAuthor"})
    public void updateAuthor(@ShellOption Long id, @ShellOption String name) {
        authorService.updateAuthor(new Author(id, name));
    }

    @ShellMethod(value = "add book to Author by ids", key = {"addBtA", "addBookToAuthor"})
    public void addBookToAuthor(@ShellOption Long authorId, @ShellOption Long bookId) {
        authorService.addBookByAuthorId(authorId, bookId);
    }

    @ShellMethod(value = "delete book from Author by ids", key = {"delBfA", "delBookFromAuthor"})
    public void deleteBookFromAuthor(@ShellOption Long authorId, @ShellOption Long bookId) {
        authorService.deleteBookByAuthorId(authorId, bookId);
    }
}
