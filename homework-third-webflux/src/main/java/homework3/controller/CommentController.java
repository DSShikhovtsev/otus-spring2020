package homework3.controller;

import homework3.domain.Comment;
import homework3.repository.CommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class CommentController {

    @Bean("commentRouter")
    public RouterFunction<ServerResponse> composedCommentResponse(CommentRepository repository) {

        CommentHandler handler = new CommentHandler(repository);

        return route()
                .GET("/flux/showComments", accept(APPLICATION_JSON), handler::comments)
                .GET("/flux/comment", accept(APPLICATION_JSON), handler::comment)
                .GET("/flux/addComment", accept(APPLICATION_JSON), handler::addComment)
                .POST("/flux/comment", handler::save)
                .POST("/flux/deleteComment", handler::delete)
                .build();
    }

    static class CommentHandler {

        private CommentRepository repository;

        CommentHandler(CommentRepository repository) {
            this.repository = repository;
        }


        public Mono<ServerResponse> comments(ServerRequest request) {
            return ok().contentType( APPLICATION_JSON ).body( repository.findAll(), Comment.class);
        }

        public Mono<ServerResponse> comment(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findById(String.valueOf(request.queryParam("id"))), Comment.class);
        }

        public Mono<ServerResponse> addComment(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(new Comment(), Comment.class);
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return null; //TODO
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return null; //TODO
        }
    }
}
