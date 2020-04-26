package homework2.dao;

import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Dao для работы с жанрами")
@DataJpaTest
@Import({GenreDaoJdbc.class})
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        assertThat(em.find(Genre.class, 1L)).isEqualToComparingFieldByField(dao.getById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void findAll() {
        Genre genre = em.find(Genre.class, 1L);
        Genre genre1 = em.find(Genre.class, 2L);
        List<Genre> genres = dao.findAll();
        assertThat(genres).hasSize(2).containsExactlyInAnyOrder(genre, genre1);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void insert() {
        dao.save(new Genre("test"));
        Genre genre = em.find(Genre.class, 3L);
        assertThat(genre).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void update() {
        Genre genre = em.find(Genre.class, 2L);
        genre.setDescription("update");
        dao.update(genre);
        em.refresh(genre);
        assertEquals(genre.getDescription(), dao.getById(2L).getDescription());
    }

    @Test
    @DisplayName("удалять жанр из таблицы")
    void deleteById() {
        Genre genre = em.find(Genre.class, 1L);
        assertThat(genre).isNotNull();
        dao.deleteById(1L);
        em.clear();
        genre = em.find(Genre.class, 1L);
        assertThat(genre).isNull();
    }
}