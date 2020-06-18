package homework3.repository;

import homework3.domain.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    void deleteByBookId(String bookId);

    Flux<Comment> findAll();

    Mono<Comment> findById(String id);

    Mono<Void> save(Comment comment);
}
