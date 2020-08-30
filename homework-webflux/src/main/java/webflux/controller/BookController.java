package webflux.controller;

import webflux.domain.Book;
import webflux.service.book.BookService;
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

    @GetMapping("/showBooks")
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
        return "addBook";
    }

    @PostMapping("/book")
    public String saveBook(Book book, @RequestParam("addAuthorId") String addAuthorId, @RequestParam("addGenreId") String addGenreId,
                           @RequestParam("delAuthorId") String delAuthorId, @RequestParam("delGenreId") String delGenreId) {
        if (book.getId() != null && book.getId().isEmpty()) {
            book.setId(null);
        } else {
            Book temp = service.getBookById(book.getId());
            book.setAuthors(temp.getAuthors());
            book.setGenres(temp.getGenres());
        }
        service.addAuthorToBook(book, addAuthorId);
        service.addGenreToBook(book, addGenreId);
        service.deleteAuthorFomBook(book, delAuthorId);
        service.deleteGenreFromBook(book, delGenreId);
        service.save(book);
        return "redirect:/showBooks";
    }

    @PostMapping("/bookDelete")
    public String deleteBook(@RequestParam("id") String id) {
        service.deleteBookById(id);
        return "redirect:/showBooks";
    }
}
