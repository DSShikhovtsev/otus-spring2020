package homework2.mapper;

import homework2.entityReference.AuthorsBooks;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorBookMapper implements RowMapper<AuthorsBooks> {

    @Override
    public AuthorsBooks mapRow(ResultSet resultSet, int i) throws SQLException {
        Long authorId = resultSet.getLong("id_author");
        Long bookId = resultSet.getLong("id_book");
        return new AuthorsBooks(authorId, bookId);
    }
}
