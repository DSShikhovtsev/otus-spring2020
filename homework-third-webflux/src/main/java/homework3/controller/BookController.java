package homework3.controller;

import homework3.domain.Book;
import homework3.repository.BookRepository;
import org.springframework.context.annotation.Bean;
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
public class BookController {

    @Bean
    public RouterFunction<ServerResponse> composeBookResponse(BookRepository repository) {

        BookHandler handler = new BookHandler(repository);

        return route()
                .GET("/flux/showBooks", accept(APPLICATION_JSON), handler::books)
                .GET("/flux/book", accept(APPLICATION_JSON), handler::book)
                .GET("/flux/addBook", accept(APPLICATION_JSON), handler::addBook)
                .POST("/flux/book", handler::save)
                .POST("/flux/deleteBook", handler::delete)
                .build();
    }

    static class BookHandler {

        private BookRepository repository;

        BookHandler(BookRepository repository) {
            this.repository = repository;
        }


        public Mono<ServerResponse> books(ServerRequest request) {
            return ok().contentType( APPLICATION_JSON ).body( repository.findAll(), Book.class);
        }

        public Mono<ServerResponse> book(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findById(String.valueOf(request.queryParam("id"))), Book.class);
        }

        public Mono<ServerResponse> addBook(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(new Book(), Book.class);
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return null; //TODO
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return null; //TODO
        }
    }
}
