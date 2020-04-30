package homework2.shell;

import homework2.domain.Book;
import homework2.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellBookCommands {

    private final BookService bookService;

    public ShellBookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "show books", key = {"shB", "showBooks"})
    public void showBooks() {
        bookService.getAllBooks().forEach(System.out::println);
    }

    @ShellMethod(value = "show book by id", key = {"sB", "showBook"})
    public void showBook(@ShellOption Long id) {
        System.out.println(bookService.getBookById(id));
    }

    @ShellMethod(value = "add book", key = {"addB", "addBook"})
    public void addBook(@ShellOption String title) {
        bookService.save(new Book(title));
    }

    @ShellMethod(value = "delete book by id", key = {"delB", "delBook", "deleteBook"})
    public void deleteBook(@ShellOption Long id) {
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "update Book title by id", key = {"updB", "updBook", "updateBook"})
    public void updateBook(@ShellOption Long id, @ShellOption String title) {
        bookService.updateBook(new Book(id, title));
    }
}
