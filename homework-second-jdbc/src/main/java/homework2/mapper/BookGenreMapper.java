package homework2.mapper;

import homework2.domain.BooksGenres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookGenreMapper implements RowMapper<BooksGenres> {
    @Override
    public BooksGenres mapRow(ResultSet resultSet, int i) throws SQLException {
        Long bookId = resultSet.getLong("id_book");
        Long genreId = resultSet.getLong("id_genre");
        return new BooksGenres(bookId, genreId);
    }
}
