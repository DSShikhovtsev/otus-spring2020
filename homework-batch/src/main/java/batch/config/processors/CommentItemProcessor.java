package batch.config.processors;

import batch.domain.mongo.Author;
import batch.domain.mongo.Book;
import batch.domain.mongo.Comment;
import batch.domain.mongo.Genre;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class CommentItemProcessor implements ItemProcessor<batch.domain.h2.Comment, Comment> {

    @Override
    public Comment process(batch.domain.h2.Comment comment) throws Exception {
        Book book = new Book(comment.getBook().getId().toString(), comment.getBook().getTitle());
        book.setAuthors(comment.getBook().getAuthors().stream().map(author -> new Author(author.getId().toString(), author.getName())).collect(Collectors.toList()));
        book.setGenres(comment.getBook().getGenres().stream().map(genre -> new Genre(genre.getId().toString(), genre.getDescription())).collect(Collectors.toList()));
        return new Comment(comment.getId().toString(), comment.getComment());
    }
}
