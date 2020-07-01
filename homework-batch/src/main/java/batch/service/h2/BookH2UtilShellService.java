package batch.service.h2;

import batch.domain.h2.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookH2UtilShellService implements BookH2UtilService {

    private final BookService bookService;

    public BookH2UtilShellService(BookService bookService) {
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
    public void delete(Long id) {
        bookService.deleteBookById(id);
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
