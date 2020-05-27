package homework2.testBee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Comment;
import homework2.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@ChangeLog
@Component
public class TestDBChangelog {

    private Author author;
    private Author author1;

    private Genre genre;
    private Genre genre1;

    private Book book;
    private Book book1;

    @ChangeSet(order = "001", id = "addAuthors", author = "temp")
    public void insertAuthors(MongoTemplate template) {
        author = template.save(new Author("1", "author"));
        author1 = template.save(new Author("2", "author1"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "temp")
    public void insertGenres(MongoTemplate template) {
        genre = template.save(new Genre("1", "genre"));
        genre1 = template.save(new Genre("2", "genre1"));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "temp")
    public void insertBooks(MongoTemplate template) {
        book = new Book("1", "book");
        book.getAuthors().add(author);
        book.getGenres().add(genre);
        book = template.save(book);

        book1 = new Book("2", "book1");
        book1.getAuthors().add(author1);
        book1.getGenres().add(genre1);
        book1 = template.save(book1);
    }

    @ChangeSet(order = "004", id = "addComments", author = "temp")
    public void insertComments(MongoTemplate template) {
        template.save(new Comment("1", "comment", book));
        template.save(new Comment("2", "comment1", book1));
    }
}
