package homework2.dao;

import homework2.domain.*;
import homework2.entityReference.AuthorsBooks;
import homework2.entityReference.BooksGenres;
import homework2.mapper.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("title", book.getTitle());
        jdbc.update("insert into books(title) values(:title)", map, keyHolder, new String[]{"id"});
        book.setId(keyHolder.getKey().longValue());
        insertIntoSubtable(book);
    }

    private void insertIntoSubtable(Book book) {
        ArrayList<BooksGenres> booksGenres = new ArrayList<>();
        book.getGenres().forEach(t -> booksGenres.add(new BooksGenres(book.getId(), t.getId())));
        jdbc.batchUpdate("insert into books_genres(id_book, id_genre) values(:idBook, :idGenre)",
                SqlParameterSourceUtils.createBatch(booksGenres.toArray()));
        ArrayList<AuthorsBooks> authorsBooks = new ArrayList<>();
        book.getGenres().forEach(t -> authorsBooks.add(new AuthorsBooks(t.getId(), book.getId())));
        jdbc.batchUpdate("insert into authors_books(id_author, id_book) values(:idAuthor, :idBook)",
                SqlParameterSourceUtils.createBatch(booksGenres.toArray()));
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        jdbc.update("update books set title = :title where id = :id", params);
    }

    @Override
    public Book getById(Long id) {
        Book book = jdbc.queryForObject("select * from books where id = :id", Collections.singletonMap("id", id), new BookMapper());
        if (book != null) {
            book.getAuthors().addAll(findAuthorsByBookId(book.getId()));
            book.getGenres().addAll(findGenresByBookId(book.getId()));
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = jdbc.query("select * from books", new BookMapper());
        List<Author> authors = jdbc.query("select * from authors where id in (select id_author from authors_books)", new AuthorMapper());
        List<AuthorsBooks> authorsBooks = jdbc.query("select * from authors_books", new AuthorBookMapper());
        List<Genre> genres = jdbc.query("select * from genres where id in (select id_genre from books_genres)", new GenreMapper());
        List<BooksGenres> booksGenres = jdbc.query("select * from books_genres", new BookGenreMapper());
        books.forEach(t -> {
            t.getAuthors().addAll(findAuthorsByBookIdLocal(t.getId(), authors, authorsBooks));
            t.getGenres().addAll(findGenresByBookIdLocal(t.getId(), genres, booksGenres));
        });
        return books;
    }

    @Override
    public void addGenreByBookId(Long bookId, Long genreId) {
        insertIntoBooksGenres(bookId, genreId);
    }

    @Override
    public void deleteGenreByBookId(Long bookId, Long genreId) {
        deleteFromBooksGenres(bookId, genreId);
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from books where id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public void addAuthorByBookId(Long bookId, Long authorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        params.put("bookId", bookId);
        jdbc.update("insert into authors_books(id_author, id_book) values(:authorId, :bookId)", params);
    }

    @Override
    public void deleteAuthorByBookId(Long bookId, Long authorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        params.put("bookId", bookId);
        jdbc.update("delete from authors_books where id_author = :authorId and id_book = :bookId", params);
    }

    private List<Author> findAuthorsByBookIdLocal(Long id, List<Author> authors, List<AuthorsBooks> authorsBooks) {
        return new ArrayList<>(authors.stream().filter(t -> {
            for (AuthorsBooks ab : authorsBooks) {
                if (ab.getIdBook().equals(id) && ab.getIdAuthor().equals(t.getId())) {
                    return  true;
                }
            }
            return false;
        }).collect(Collectors.toList()));
    }

    private List<Genre> findGenresByBookIdLocal(Long id, List<Genre> genres, List<BooksGenres> booksGenres) {
        return new ArrayList<>(genres.stream().filter(t -> {
            for (BooksGenres bg : booksGenres) {
                if (bg.getIdBook().equals(id) && bg.getIdGenre().equals(t.getId())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList()));
    }

    private List<Author> findAuthorsByBookId(Long id) {
        return jdbc.query("select * from authors where id in " +
                "(select id_author from authors_books where id_book = :id)", Collections.singletonMap("id", id), new AuthorMapper());
    }

    private List<Genre> findGenresByBookId(Long id) {
        return jdbc.query("select * from genres where id in" +
                " (select id_genre from books_genres where id_book = :id)", Collections.singletonMap("id", id), new GenreMapper());
    }

    public void insertIntoBooksGenres(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        jdbc.update("insert into books_genres(id_book, id_genre) values(:bookId, :genreId)", params);
    }

    private void deleteFromBooksGenres(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        jdbc.update("delete from books_genres where id_book = :bookId and id_genre = :genreId", params);
    }
}
