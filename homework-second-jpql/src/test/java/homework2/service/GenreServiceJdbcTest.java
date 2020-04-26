package homework2.service;

import homework2.dao.GenreDaoJdbc;
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

@DisplayName("Service для работы с жанрами")
@DataJpaTest
@Import({GenreServiceJdbc.class, GenreDaoJdbc.class})
class GenreServiceJdbcTest {

    @Autowired
    private GenreServiceJdbc service;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        assertThat(em.find(Genre.class, 1L)).isEqualToComparingFieldByField(service.getGenreById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void findAll() {
        Genre genre = em.find(Genre.class, 1L);
        Genre genre1 = em.find(Genre.class, 2L);
        List<Genre> genres = service.getAllGenres();
        assertThat(genres).hasSize(2).containsExactlyInAnyOrder(genre, genre1);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void insert() {
        service.save(new Genre("test"));
        Genre genre = em.find(Genre.class, 3L);
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(3L));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void update() {
        Genre genre = em.find(Genre.class, 2L);
        genre.setDescription("update");
        service.updateGenre(genre);
        em.refresh(genre);
        assertEquals(genre.getDescription(), service.getGenreById(2L).getDescription());
    }

    @Test
    @DisplayName("удалять жанр из таблицы")
    void deleteById() {
        Genre genre = em.find(Genre.class, 1L);
        assertThat(genre).isNotNull();
        service.deleteGenreById(1L);
        em.clear();
        genre = em.find(Genre.class, 1L);
        assertThat(genre).isNull();
    }
}