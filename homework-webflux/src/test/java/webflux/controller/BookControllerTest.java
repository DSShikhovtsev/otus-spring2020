package webflux.controller;

import webflux.domain.Author;
import webflux.domain.Book;
import webflux.domain.Genre;
import webflux.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller для работы с книгами")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService service;

    @BeforeEach
    void setUp() {
        Mockito.reset(service);
        List<Book> books = new ArrayList<>();
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        books.add(book);
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        books.add(book1);
        Book book2 = new Book("2", "test");
        book2.getAuthors().add(new Author("2", "test1"));
        book2.getGenres().add(new Genre("2", "genre1"));
        given(service.getBookById("1")).willReturn(book);
        given(service.getBookById("2")).willReturn(book2);
        given(service.getBookById("3")).willReturn(new Book("3", "testInsert"));
        given(service.getAllBooks()).willReturn(books);
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void showBooks() throws Exception  {
        List<Book> books = new ArrayList<>();
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        books.add(book);
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        books.add(book1);
        String booksList = Objects.requireNonNull(this.mvc.perform(get("/showBooks")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("books").toString();
        assertEquals(booksList, books.toString());
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void showBook() throws Exception  {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Book returned = (Book) Objects.requireNonNull(this.mvc.perform(get("/book?id=1")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("book");
        assertEquals(book, returned);
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void addBook() throws Exception  {
        Book book = new Book("3", "testInsert");
        this.mvc.perform(post("/book?id=&title=testInsert&addAuthorId=&addGenreId=&delAuthorId=&delGenreId=")).andExpect(status().isFound());
        List<Book> returned = (List<Book>) Objects.requireNonNull(this.mvc.perform(get("/showBooks")).andReturn()
                .getModelAndView()).getModelMap().getAttribute("books");
        assertThat(returned.contains(book));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void updateBook() throws Exception  {
        Book book = new Book("2", "test");
        book.getAuthors().add(new Author("2", "test1"));
        book.getGenres().add(new Genre("2", "genre1"));
        Book returned = (Book) Objects.requireNonNull(this.mvc.perform(get("/book?id=2")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("book");
        assertEquals(book, returned);
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteBook() throws Exception  {
        this.mvc.perform(MockMvcRequestBuilders
                .post("/bookDelete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}