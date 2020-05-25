package homework3.controller;

import homework3.domain.Book;
import homework3.service.book.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/showBook")
    public String showBooks(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "booksPage";
    }

    @GetMapping("/book")
    public String showBook(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", service.getBookById(id));
        return "book";
    }

    @GetMapping("addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @PostMapping("/book")
    public String saveBook(Book book, Model model) {
        if (book.getId().isEmpty()) {
            book.setId(null);
        } else {
            Book temp = service.getBookById(book.getId());
            book.setAuthors(temp.getAuthors());
            book.setGenres(temp.getGenres());
        }
        service.save(book);
        model.addAttribute("books", service.getAllBooks());
        return "booksPage";
    }

    @PostMapping("/bookDelete")
    public String deleteBook(@RequestParam("id") String id, Model model) {
        service.deleteBookById(id);
        model.addAttribute("books", service.getAllBooks());
        return "booksPage";
    }
}
