package homework2.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorsBooksJdbc implements AuthorsBooksDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorsBooksJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insertIntoAuthorsBooks(Long authorId, Long bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        params.put("bookId", bookId);
        jdbc.update("insert into authors_books(id_author, id_book) values(:authorId, :bookId)", params);
    }

    @Override
    public void deleteFromAuthorsBooks(Long authorId, Long bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        params.put("bookId", bookId);
        jdbc.update("delete from authors_books where id_author = :authorId and id_book = :bookId", params);
    }

    @Override
    public void deleteByBookId(Long id) {
        jdbc.update("delete from authors_books where id_book = :bookId", Collections.singletonMap("bookId", id));
    }

    @Override
    public void deleteByAuthorId(Long id) {
        jdbc.update("delete from authors_books where id_author = :authorId", Collections.singletonMap("authorId", id));
    }
}
