package homework3.repository;

import homework3.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@DisplayName("Repository для работы с жанрами")
@DataMongoTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @DisplayName("при сохранении должен генерировать ID")
    @Test
    public void saveGenre() {
        Mono<Genre> genreMono = repository.save(new Genre("genre"));

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertNotNull(genre.getId()))
                .expectComplete()
                .verify();
    }
}
