package homework3.controller;

import homework3.domain.Author;
import homework3.repository.AuthorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
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
public class AuthorController {

    @Bean
    public RouterFunction<ServerResponse> composedAuthorResponse(AuthorRepository repository) {
        
        AuthorHandler handler = new AuthorHandler(repository);
        
        return route()
                .GET("/flux/showAuthors", accept(APPLICATION_JSON), handler::authors)
                .GET("/flux/author", accept(APPLICATION_JSON), handler::author)
                .GET("/flux/addAuthor", accept(APPLICATION_JSON), handler::addAuthor)
                .POST("/flux/author", handler::save)
                .POST("/flux/deleteAuthor", handler::delete)
                .build();
    }
    
    
    static class AuthorHandler {
        
        private AuthorRepository repository;

        AuthorHandler(AuthorRepository repository) {
            this.repository = repository;
        }
        
        public Mono<ServerResponse> authors(ServerRequest request) {
            return ok().contentType( APPLICATION_JSON ).body( repository.findAll(), Author.class );
        }

        public Mono<ServerResponse> author(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findById(String.valueOf(request.queryParam("id"))), Author.class);
        }

        public Mono<ServerResponse> addAuthor(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(new Author(), Author.class);
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return null; //TODO
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return null; //TODO
        }
    }
}
