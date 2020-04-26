package homework2.service;

import homework2.dao.BookDaoJdbc;
import homework2.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service для работы с книгами")
@DataJpaTest
@Import({BookServiceJdbc.class, BookDaoJdbc.class})
class BookServiceJdbcTest {

    @Autowired
    private BookServiceJdbc service;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getById() {
        assertThat(em.find(Book.class, 1L)).isEqualToComparingFieldByField(service.getBookById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void findAll() {
        Book book = em.find(Book.class, 1L);
        Book book1 = em.find(Book.class, 2L);
        List<Book> books = service.getAllBooks();
        assertThat(books).hasSize(2).containsExactlyInAnyOrder(book, book1);
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void insert() {
        service.save(new Book("test"));
        Book book = em.find(Book.class, 3L);
        assertThat(book).isEqualToComparingFieldByField(service.getBookById(3L));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void update() {
        Book book = em.find(Book.class, 2L);
        book.setTitle("test");
        service.updateBook(book);
        em.refresh(book);
        assertEquals(book.getTitle(), service.getBookById(2L).getTitle());
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteById() {
        Book book = em.find(Book.class, 1L);
        assertThat(book).isNotNull();
        service.deleteBookById(1L);
        em.clear();
        book = em.find(Book.class, 1L);
        assertThat(book).isNull();
    }
}