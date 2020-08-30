package webflux.service.book;

import webflux.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookUtilRestService implements BookUtilService {

    private final BookService bookService;

    public BookUtilRestService(BookService bookService) {
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void showAll() {
        bookService.getAllBooks().forEach(System.out::println);
    }

    @Transactional
    @Override
    public void showInstance(String id) {
        System.out.println(bookService.getBookById(id));
    }

    @Transactional
    @Override
    public void delete(String id) {
        bookService.deleteBookById(id);
    }

    @Transactional
    @Override
    public void add(String title) {
        bookService.save(new Book(title));
    }

    @Transactional
    @Override
    public void update(String id, String title) {
        bookService.updateBook(new Book(id, title));
    }
}
