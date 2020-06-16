package homework3.controller;

import homework3.domain.Comment;
import homework3.repository.BookRepository;
import homework3.repository.CommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class CommentController {

    @Bean("commentRouter")
    public RouterFunction<ServerResponse> composedCommentResponse(CommentRepository repository, BookRepository bookRepository) {

        CommentHandler handler = new CommentHandler(repository, bookRepository);

        return route()
                .GET("/flux/showComments", accept(APPLICATION_JSON), handler::comments)
                .GET("/flux/comment/{id}", accept(APPLICATION_JSON), handler::comment)
                .GET("/flux/addComment", accept(APPLICATION_JSON), handler::addComment)
                .POST("/flux/comment", handler::save)
                .POST("/flux/deleteComment", handler::delete)
                .build();
    }

    static class CommentHandler {

        private CommentRepository repository;
        private BookRepository bookRepository;

        CommentHandler(CommentRepository repository, BookRepository bookRepository) {
            this.repository = repository;
            this.bookRepository = bookRepository;
        }


        public Mono<ServerResponse> comments(ServerRequest request) {
            return ok().contentType( APPLICATION_JSON ).body( repository.findAll(), Comment.class);
        }

        public Mono<ServerResponse> comment(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findById(request.pathVariable("id")), Comment.class);
        }

        public Mono<ServerResponse> addComment(ServerRequest request) {
            return Mono.just(new Comment()).flatMap(comment -> ok().contentType(APPLICATION_JSON).body(fromValue(comment)));
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return request.bodyToMono(Comment.class)
                    .map(comment -> {
                        if (comment.getId().isEmpty()) comment.setId(null);
                        comment.setBook(bookRepository.findById(String.valueOf(request.queryParam("idBook"))).blockOptional().orElse(null));
                        return repository.save(comment);
                    })
                    .flatMap(comment -> ok().body(fromValue(comment)));
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return request.bodyToMono(Comment.class)
                    .map(repository::delete)
                    .flatMap(comment -> ok().body(fromValue(comment)));
        }
    }
}
