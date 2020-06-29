package homework3.service.book;

import homework3.domain.Book;
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
    public void showInstance(Long id) {
        System.out.println(bookService.getBookById(id));
    }

    @Transactional
    @Override
    public void delete(Book book) {
        bookService.deleteBook(book);
    }

    @Transactional
    @Override
    public void add(String title) {
        bookService.save(new Book(title));
    }

    @Transactional
    @Override
    public void update(Long id, String title) {
        bookService.updateBook(new Book(id, title));
    }
}
