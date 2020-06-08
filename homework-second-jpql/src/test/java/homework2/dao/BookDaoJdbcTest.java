package homework2.dao;

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

@DisplayName("Dao для работы с книгами")
@DataJpaTest
@Import({BookDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getById() {
        assertThat(em.find(Book.class, 1L)).isEqualToComparingFieldByField(dao.getById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void findAll() {
        Book book = em.find(Book.class, 1L);
        Book book1 = em.find(Book.class, 2L);
        List<Book> books = dao.findAll();
        assertThat(books).hasSize(2).containsExactlyInAnyOrder(book, book1);
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void insert() {
        dao.save(new Book("test"));
        Book book = em.find(Book.class, 3L);
        assertThat(book).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void update() {
        Book book = em.find(Book.class, 2L);
        book.setTitle("test");
        dao.update(book);
        em.refresh(book);
        assertEquals(book.getTitle(), dao.getById(2L).getTitle());
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteById() {
        Book book = em.find(Book.class, 1L);
        assertThat(book).isNotNull();
        dao.deleteById(1L);
        em.clear();
        book = em.find(Book.class, 1L);
        assertThat(book).isNull();
    }
}