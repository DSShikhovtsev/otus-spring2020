package homework2.dao;

import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Dao для работы с жанрами")
@RunWith(SpringRunner.class)
@DataJpaTest
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao dao;

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        Genre genre = new Genre(1L, "genre");
        assertThat(genre).isEqualToComparingFieldByField(dao.findById(1L).get());
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void findAll() {
        Genre genre = new Genre(1L, "genre");
        Genre genre1 = new Genre(2L, "genre1");
        List<Genre> genres = dao.findAll();
        assertThat(genres).hasSize(2).containsExactlyInAnyOrder(genre, genre1);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void insert() {
        dao.save(new Genre("test"));
        Genre genre = dao.findById(3L).orElse(null);
        assertThat(genre).isEqualToComparingFieldByField(dao.findById(3L).get());
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void update() {
        Genre genre = dao.findById(2L).get();
        genre.setDescription("update");
        dao.save(genre);
        assertEquals(genre.getDescription(), dao.findById(2L).get().getDescription());
    }

    @Test
    @DisplayName("удалять жанр из таблицы")
    void deleteById() {
        Genre genre = dao.findById(1L).orElse(null);
        assertThat(genre).isNotNull();
        dao.deleteById(1L);
        genre = dao.findById(1L).orElse(null);
        assertThat(genre).isNull();
    }
}