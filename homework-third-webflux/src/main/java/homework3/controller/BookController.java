package homework3.controller;

import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Genre;
import homework3.repository.AuthorRepository;
import homework3.repository.BookRepository;
import homework3.repository.CommentRepository;
import homework3.repository.GenreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class BookController {

    @Bean("bookRouter")
    public RouterFunction<ServerResponse> composeBookResponse(BookRepository repository,
                                                              AuthorRepository authorRepository,
                                                              GenreRepository genreRepository,
                                                              CommentRepository commentRepository) {

        BookHandler handler = new BookHandler(repository, authorRepository, genreRepository, commentRepository);

        return route()
                .GET("/flux/showBooks", accept(APPLICATION_JSON), handler::books)
                .GET("/flux/book/{id}", accept(APPLICATION_JSON), handler::book)
                .GET("/flux/addBook", accept(APPLICATION_JSON), handler::addBook)
                .POST("/flux/book", handler::save)
                .POST("/flux/deleteBook/{id}", handler::delete)
                .build();
    }

    static class BookHandler {

        private BookRepository repository;
        private AuthorRepository authorRepository;
        private GenreRepository genreRepository;
        private CommentRepository commentRepository;

        BookHandler(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
            this.repository = repository;
            this.authorRepository = authorRepository;
            this.genreRepository = genreRepository;
            this.commentRepository = commentRepository;
        }

        public Mono<ServerResponse> books(ServerRequest request) {
            return ok().contentType( APPLICATION_JSON ).body( repository.findAll(), Book.class);
        }

        public Mono<ServerResponse> book(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findById(request.pathVariable("id")), Book.class);
        }

        public Mono<ServerResponse> addBook(ServerRequest request) {
            return Mono.just(new Book()).flatMap(book -> ok().contentType(APPLICATION_JSON).body(fromValue(book)));
        }

        public Mono<ServerResponse> save(ServerRequest request) {
            return request.bodyToMono(Book.class)
                    .flatMap(book -> {
                        if (book.getId() != null && book.getId().isEmpty()) book.setId(null);
                        Mono<Author> authorAdd = authorRepository.findById(String.valueOf(request.queryParam("addAuthorId")));
                        if (authorAdd != null) book.getAuthors().add(authorAdd.block());
                        Mono<Genre> genreAdd = genreRepository.findById(String.valueOf(request.queryParam("addGenreId")));
                        if (genreAdd != null) book.getGenres().add(genreAdd.block());
                        Mono<Author> authorDelete = authorRepository.findById(String.valueOf(request.queryParam("delAuthorId")));
                        if (authorDelete != null) book.getAuthors().remove(authorDelete.block());
                        Mono<Genre> genreDelete = genreRepository.findById(String.valueOf(request.queryParam("delGenreId")));
                        if (genreDelete != null) book.getGenres().remove(genreDelete.block());
                        return ok().build(repository.save(book));
                    });
        }

        public Mono<ServerResponse> delete(ServerRequest request) {
            return repository.deleteById(request.pathVariable("id"))
                    .flatMap(book -> {
                        commentRepository.deleteByBookId(request.pathVariable("id"));
                        return ok().build();
                    });
        }
    }
}
