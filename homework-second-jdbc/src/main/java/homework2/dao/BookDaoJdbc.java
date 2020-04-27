package homework2.dao;

import homework2.domain.*;
import homework2.entityReference.AuthorsBooks;
import homework2.entityReference.BooksGenres;
import homework2.mapper.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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
        jdbc.update("insert into books(title) values(:title)", Collections.singletonMap("title", book.getTitle()));
        Long id = findMaxId();
        book.getGenres().forEach(t -> insertIntoBooksGenres(id, t.getId()));
    }

    private Long findMaxId() {
        return jdbc.queryForObject("select max(id) from books", Collections.emptyMap(), Long.class);
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
            book.getGenres().addAll(findGenresByBookId(book.getId()));
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = jdbc.query("select * from books", new BookMapper());
        List<Genre> genres = jdbc.query("select * from genres where id in (select id_genre from books_genres)", new GenreMapper());
        List<BooksGenres> booksGenres = jdbc.query("select * from books_genres", new BookGenreMapper());
        books.forEach(t -> t.getGenres().addAll(findGenresByBookIdLocal(t.getId(), genres, booksGenres)));
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
        deleteFromAuthorsBooksByBookId(id);
        deleteFromBooksGenresByBookId(id);
        jdbc.update("delete from books where id = :id", Collections.singletonMap("id", id));
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

    private List<Genre> findGenresByBookId(Long id) {
        return jdbc.query("select * from genres where id in" +
                " (select id_genre from books_genres where id_book = :id)", Collections.singletonMap("id", id), new GenreMapper());
    }

    private void deleteFromAuthorsBooksByBookId(Long id) {
        jdbc.update("delete from authors_books where id_book = :bookId", Collections.singletonMap("bookId", id));
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

    private void deleteFromBooksGenresByBookId(Long id) {
        jdbc.update("delete from books_genres where id_book = :bookId", Collections.singletonMap("bookId", id));
    }
}
