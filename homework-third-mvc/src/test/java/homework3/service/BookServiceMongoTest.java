package homework3.service;

import homework3.AbstractServiceTest;
import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Genre;
import homework3.service.book.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service для работы с книгами")
class BookServiceMongoTest extends AbstractServiceTest {

    @Autowired
    private BookService service;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getBookById() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "author"));
        book.getGenres().add(new Genre("1", "genre"));
        assertThat(book).isEqualToComparingFieldByField(service.getBookById("1"));
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void getAllBooks() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "author"));
        book.getGenres().add(new Genre("1", "genre"));
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "author1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        assertThat(service.getAllBooks()).hasSize(2).containsExactlyInAnyOrder(book, book1);
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void save() {
        Book book = new Book("3", "test");
        service.save(book);
        assertThat(book).isEqualToComparingFieldByField(service.getBookById("3"));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void updateBook() {
        Book book = service.getBookById("2");
        book.setTitle("testInsert");
        service.updateBook(book);
        assertThat(service.getBookById("2")).isEqualToComparingFieldByField(book);
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteBookById() {
        Book book = service.getBookById("2");
        assertThat(book).isNotNull();
        service.deleteBookById("2");
        book = service.getBookById("2");
        assertThat(book).isNull();
    }
}