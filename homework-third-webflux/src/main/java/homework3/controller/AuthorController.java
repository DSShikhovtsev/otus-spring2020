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
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class AuthorController {

    @Bean("authorRouter")
    public RouterFunction<ServerResponse> composedAuthorResponse(AuthorRepository repository) {
        
        AuthorHandler handler = new AuthorHandler(repository);
        
        return route()
                .GET("/flux/showAuthors", accept(APPLICATION_JSON), handler::authors)
                .GET("/flux/author/{id}", accept(APPLICATION_JSON), handler::author)
                .GET("/flux/addAuthor", accept(APPLICATION_JSON), handler::addAuthor)
                .POST("/flux/author", accept(APPLICATION_JSON), handler::save)
                .POST("/flux/deleteAuthor/{id}", handler::delete)
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
            return repository.findById(request.pathVariable("id"))
                    .flatMap(author -> ok().contentType(APPLICATION_JSON).body(fromValue(author)));
        }

        public Mono<ServerResponse> addAuthor(ServerRequest request) {
            return Mono.just(new Author()).flatMap(author -> ok().contentType(APPLICATION_JSON).body(fromValue(author)));
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return request.bodyToMono(Author.class)
                    .flatMap(author -> {
                        if (author.getId() != null && author.getId().isEmpty()) author.setId(null);
                        return ok().build(repository.save(author));
                    });
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return repository.deleteById(request.pathVariable("id"))
                    .flatMap(author -> ok().build());
        }
    }
}
