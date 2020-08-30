package webflux.controller.rest;

import webflux.domain.Book;
import webflux.service.book.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    private final BookService service;

    public BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping("/api/books")
    public List<Book> showBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/api/books/{id}")
    public Book showBook(@PathVariable("id") String id) {
        return service.getBookById(id);
    }

    @PostMapping("/api/books")
    public void saveBook(@RequestBody Book book) {
        service.save(book);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        service.deleteBookById(id);
    }
}
