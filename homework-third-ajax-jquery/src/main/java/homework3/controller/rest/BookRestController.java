package homework3.controller.rest;

import homework3.domain.Book;
import homework3.service.book.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController {

    private final BookService service;

    public BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping("/api/showBooks")
    public List<Book> showBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/api/book")
    public Book showBook(@RequestParam("id") String id) {
        return service.getBookById(id);
    }
}
