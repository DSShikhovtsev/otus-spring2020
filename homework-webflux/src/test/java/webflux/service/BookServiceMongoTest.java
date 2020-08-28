package webflux.service;

import webflux.domain.Author;
import webflux.domain.Book;
import webflux.domain.Genre;
import webflux.repository.AuthorRepository;
import webflux.repository.BookRepository;
import webflux.repository.CommentRepository;
import webflux.repository.GenreRepository;
import webflux.service.book.BookService;
import webflux.service.book.BookServiceMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с книгами")
@SpringBootTest(classes = BookServiceMongo.class)
class BookServiceMongoTest {

    @Autowired
    private BookService service;

    @MockBean
    private BookRepository repository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        List<Book> books = new ArrayList<>();
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        books.add(book);
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        books.add(book1);
        Mockito.when(repository.findById("1")).thenReturn(Optional.of(book)).thenReturn(Optional.empty());
        given(repository.findById("2")).willReturn(Optional.of(book1));
        given(repository.findById("3")).willReturn(Optional.of(new Book("3", "test")));
        given(repository.findAll()).willReturn(books);
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getBookById() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        assertThat(book).isEqualToComparingFieldByField(service.getBookById("1"));
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void getAllBooks() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        assertThat(service.getAllBooks()).hasSize(2).containsExactlyInAnyOrder(book, book1);
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void addBook() {
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
        Book book = service.getBookById("1");
        assertThat(book).isNotNull();
        service.deleteBookById("1");
        book = service.getBookById("1");
        assertThat(book).isNull();
    }
}