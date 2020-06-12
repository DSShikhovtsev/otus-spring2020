package homework3.repository;

import homework3.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@DisplayName("Repository для работы с комментариями")
@DataMongoTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository repository;

    @DisplayName("при сохранении должен генерировать ID")
    @Test
    public void saveComment() {
        Mono<Comment> commentMono = repository.save(new Comment("comment"));

        StepVerifier
                .create(commentMono)
                .assertNext(comment -> assertNotNull(comment.getId()))
                .expectComplete()
                .verify();
    }
}
