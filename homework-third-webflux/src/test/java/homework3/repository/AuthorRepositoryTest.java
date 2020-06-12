package homework3.repository;

import homework3.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@DisplayName("Repository для работы с авторами")
@DataMongoTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @DisplayName("при сохранении должен генерировать ID")
    @Test
    public void saveAuthor() {
        Mono<Author> authorMono = repository.save(new Author("author"));

        StepVerifier
                .create(authorMono)
                .assertNext(author -> assertNotNull(author.getId()))
                .expectComplete()
                .verify();
    }
}
