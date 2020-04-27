package homework2.dao;

import homework2.domain.Author;
import homework2.domain.Book;
import homework2.mapper.AuthorMapper;
import homework2.mapper.BookMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> authorParams = new HashMap<>();
        authorParams.put("name", author.getName());
        jdbc.update("insert into authors(name) values(:name)", authorParams);
        Long id = findMaxId();
        author.getBooks().forEach(book -> insertIntoAuthorsBooks(id, book.getId()));
    }

    private Long findMaxId() {
        return jdbc.queryForObject("select max(id) from authors", Collections.emptyMap(), Long.class);
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("name", author.getName());
        jdbc.update("update authors set name = :name where id = :id", params);
    }

    @Override
    public Author getById(Long id) {
        Author author = jdbc.queryForObject("select * from authors where id = :id", Collections.singletonMap("id", id), new AuthorMapper());
        if (author != null) {
            author.getBooks().addAll(findBooksByAuthorId(author.getId()));
        }
        return author;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = jdbc.query("select * from authors", new AuthorMapper());
        authors.forEach(t -> t.getBooks().addAll(findBooksByAuthorId(t.getId())));
        return authors;
    }

    private List<Book> findBooksByAuthorId(Long id) {
        return jdbc.query("select * from books where id in " +
                "(select id_book from authors_books where id_author = :id)", Collections.singletonMap("id", id), new BookMapper());
    }

    @Override
    public void deleteById(Long id) {
        deleteByAuthorId(id);
        jdbc.update("delete from authors where id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) {
        insertIntoAuthorsBooks(authorId, bookId);
    }

    @Override
    public void deleteBookFromAuthor(Long authorId, Long bookId) {
        deleteFromAuthorsBooks(authorId, bookId);
    }

    private void insertIntoAuthorsBooks(Long authorId, Long bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        params.put("bookId", bookId);
        jdbc.update("insert into authors_books(id_author, id_book) values(:authorId, :bookId)", params);
    }

    public void deleteFromAuthorsBooks(Long authorId, Long bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        params.put("bookId", bookId);
        jdbc.update("delete from authors_books where id_author = :authorId and id_book = :bookId", params);
    }

    private void deleteByAuthorId(Long id) {
        jdbc.update("delete from authors_books where id_author = :authorId", Collections.singletonMap("authorId", id));
    }
}
