package homework3.controller;

import homework3.domain.Genre;
import homework3.repository.GenreRepository;
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
public class GenreController {

    @Bean("genreRouter")
    public RouterFunction<ServerResponse> composedGenreResponse(GenreRepository repository) {

        GenreHandler handler = new GenreHandler(repository);

        return route()
                .GET("/flux/showGenres", accept(APPLICATION_JSON), handler::genres)
                .GET("/flux/genre/{id}", accept(APPLICATION_JSON), handler::genre)
                .GET("/flux/addGenre", accept(APPLICATION_JSON), handler::addGenre)
                .POST("/flux/genre", handler::save)
                .POST("/flux/deleteGenre/{id}", handler::delete)
                .build();
    }

    static class GenreHandler {

        private GenreRepository repository;

        GenreHandler(GenreRepository repository) {
            this.repository = repository;
        }

        public Mono<ServerResponse> genres(ServerRequest request) {
            return ok().contentType( APPLICATION_JSON ).body( repository.findAll(), Genre.class);
        }

        public Mono<ServerResponse> genre(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findById(request.pathVariable("id")), Genre.class);
        }

        public Mono<ServerResponse> addGenre(ServerRequest request) {
            return Mono.just(new Genre()).flatMap(genre -> ok().contentType(APPLICATION_JSON).body(fromValue(genre)));
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return request.bodyToMono(Genre.class)
                    .flatMap(genre -> {
                        if (genre.getId() != null && genre.getId().isEmpty()) genre.setId(null);
                        return ok().build(repository.save(genre));
                    });
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return repository.deleteById(request.pathVariable("id"))
                    .flatMap(genre -> ok().build());
        }
    }
}
