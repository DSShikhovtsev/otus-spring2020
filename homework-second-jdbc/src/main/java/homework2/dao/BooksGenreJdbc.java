package homework2.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BooksGenreJdbc implements BooksGenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public BooksGenreJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insertIntoBooksGenres(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        jdbc.update("insert into authors_books(id_book, id_genre) values(:bookId, :genreId)", params);
    }

    @Override
    public void deleteFromBooksGenres(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        jdbc.update("delete from books_genres where id_book = :bookId and id_genre = :genreId", params);
    }

    @Override
    public void deleteByBookId(Long id) {
        jdbc.update("delete from books_genres where id_book = :bookId", Collections.singletonMap("bookId", id));
    }

    @Override
    public void deleteByGenreId(Long id) {
        jdbc.update("delete from books_genres where id_genre = :genreId", Collections.singletonMap("genreId", id));
    }
}
