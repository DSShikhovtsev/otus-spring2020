package homework3.service;

import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Genre;
import homework3.repository.AuthorRepository;
import homework3.repository.BookRepository;
import homework3.repository.CommentRepository;
import homework3.repository.GenreRepository;
import homework3.service.book.BookService;
import homework3.service.book.BookServiceJpa;
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
@SpringBootTest(classes = BookServiceJpa.class)
class BookServiceJpaTest {

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
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(1L, "genre"));
        books.add(book);
        Book book1 = new Book(2L, "book1");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(2L, "genre1"));
        books.add(book1);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(book)).thenReturn(Optional.empty());
        given(repository.findById(2L)).willReturn(Optional.of(book1));
        given(repository.findById(3L)).willReturn(Optional.of(new Book(3L, "test")));
        given(repository.findAll()).willReturn(books);
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getBookById() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(1L, "genre"));
        assertThat(book).isEqualToComparingFieldByField(service.getBookById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void getAllBooks() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(1L, "genre"));
        Book book1 = new Book(2L, "book1");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(2L, "genre1"));
        assertThat(service.getAllBooks()).hasSize(2).containsExactlyInAnyOrder(book, book1);
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void addBook() {
        Book book = new Book(3L, "test");
        service.save(book);
        assertThat(book).isEqualToComparingFieldByField(service.getBookById(3L));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void updateBook() {
        Book book = service.getBookById(2L);
        book.setTitle("testInsert");
        service.updateBook(book);
        assertThat(service.getBookById(2L)).isEqualToComparingFieldByField(book);
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteBookById() {
        Book book = service.getBookById(1L);
        assertThat(book).isNotNull();
        service.deleteBook(book);
        book = service.getBookById(1L);
        assertThat(book).isNull();
    }
}