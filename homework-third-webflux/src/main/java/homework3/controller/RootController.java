package homework3.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Controller
public class RootController {

    @GetMapping("/")
    public String startPage() {
        return "startPage";
    }

    @Bean
    public RouterFunction<ServerResponse> composedResponse(@Qualifier("authorRouter") RouterFunction<ServerResponse> author,
                                                           @Qualifier("bookRouter") RouterFunction<ServerResponse> book,
                                                           @Qualifier("commentRouter") RouterFunction<ServerResponse> comment,
                                                           @Qualifier("genreRouter") RouterFunction<ServerResponse> genre) {
        return author.and(book).and(comment).and(genre);
    }
}
