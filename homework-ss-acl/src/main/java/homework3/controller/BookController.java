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

    @GetMapping("/showBooks")
    public String showBooks(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "booksPage";
    }

    @GetMapping("/book")
    public String showBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", service.getBookById(id));
        return "book";
    }

    @GetMapping("addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @PostMapping("/book")
    public String saveBook(Book book, @RequestParam("addAuthorId") Long addAuthorId, @RequestParam("addGenreId") Long addGenreId,
                           @RequestParam("delAuthorId") Long delAuthorId, @RequestParam("delGenreId") Long delGenreId) {
        if (book.getId() != null) {
            Book temp = service.getBookById(book.getId());
            book.setAuthors(temp.getAuthors());
            book.setGenres(temp.getGenres());
        }
        service.addAuthorToBook(book, addAuthorId);
        service.addGenreToBook(book, addGenreId);
        service.deleteAuthorFromBook(book, delAuthorId);
        service.deleteGenreFromBook(book, delGenreId);
        service.save(book);
        return "redirect:/showBooks";
    }

    @PostMapping("/bookDelete")
    public String deleteBook(Book book) {
        service.deleteBook(book);
        return "redirect:/showBooks";
    }
}
