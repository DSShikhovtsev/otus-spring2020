package batch.config.processors;

import batch.domain.mongo.Author;
import batch.domain.mongo.Book;
import batch.domain.mongo.Genre;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class BookItemProcessor implements ItemProcessor<batch.domain.h2.Book, Book> {

    @Override
    public Book process(batch.domain.h2.Book book) throws Exception {
        Book ret = new Book(book.getId().toString(), book.getTitle());
        ret.setAuthors(book.getAuthors().stream().map(author -> new Author(author.getId().toString(), author.getName())).collect(Collectors.toList()));
        ret.setGenres(book.getGenres().stream().map(genre -> new Genre(genre.getId().toString(), genre.getDescription())).collect(Collectors.toList()));
        return ret;
    }
}
