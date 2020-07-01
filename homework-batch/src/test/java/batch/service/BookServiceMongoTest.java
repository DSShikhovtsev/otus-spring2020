package batch.service;

import batch.domain.mongo.Author;
import batch.domain.mongo.Book;
import batch.domain.mongo.Genre;
import batch.reposinories.mongo.BookMongoRepository;
import batch.reposinories.mongo.CommentMongoRepository;
import batch.service.mongo.BookService;
import batch.service.mongo.BookServiceMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с книгами")
@SpringBootTest(classes = BookServiceMongo.class)
class BookServiceMongoTest {

    @Autowired
    private BookService service;

    @MockBean
    private BookMongoRepository repository;
    @MockBean
    private CommentMongoRepository commentMongoRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        List<Book> books = new ArrayList<>();
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "author"));
        book.getGenres().add(new Genre("1", "genre"));
        books.add(book);
        Book book1 = new Book("2", "testInsert");
        book1.getAuthors().add(new Author("2", "author1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        books.add(book1);
        Mockito.when(repository.findById("1")).thenReturn(book).thenReturn(null);
        Mockito.when(repository.findById("2")).thenReturn(book1).thenReturn(null);
        given(repository.findById("3")).willReturn(new Book("3", "test"));
        given(repository.findAll()).willReturn(books);
    }

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
        Book book1 = new Book("2", "testInsert");
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
        Book book = new Book("2", "book1");
        book.getAuthors().add(new Author("2", "author1"));
        book.getGenres().add(new Genre("2", "genre1"));
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