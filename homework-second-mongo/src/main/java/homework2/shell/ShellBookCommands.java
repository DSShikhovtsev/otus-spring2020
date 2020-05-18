package homework2.shell;

import homework2.service.BookUtilService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellBookCommands {

    private final BookUtilService bookService;

    public ShellBookCommands(BookUtilService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "show books", key = {"shB", "showBooks"})
    public void showBooks() {
        bookService.showAll();
    }

    @ShellMethod(value = "show book by id", key = {"sB", "showBook"})
    public void showBook(@ShellOption String id) {
        bookService.showInstance(id);
    }

    @ShellMethod(value = "add book", key = {"addB", "addBook"})
    public void addBook(@ShellOption String title) {
        bookService.add(title);
    }

    @ShellMethod(value = "delete book by id", key = {"delB", "delBook", "deleteBook"})
    public void deleteBook(@ShellOption String id) {
        bookService.delete(id);
    }

    @ShellMethod(value = "update Book title by id", key = {"updB", "updBook", "updateBook"})
    public void updateBook(@ShellOption String id, @ShellOption String title) {
        bookService.update(id, title);
    }
}
