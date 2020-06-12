package homework3.repository;

import homework3.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@DisplayName("Repository для работы с книгами")
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @DisplayName("при сохранении должен генерировать ID")
    @Test
    public void saveBook() {
        Mono<Book> bookMono = repository.save(new Book("book"));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }
}
