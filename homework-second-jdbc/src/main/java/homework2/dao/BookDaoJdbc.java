package homework2.dao;

import homework2.domain.*;
import homework2.mapper.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorsBooksDao authorsBooksDao;
    private final BooksGenreDao booksGenreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorsBooksDao authorsBooksDao, BooksGenreDao booksGenreDao) {
        this.jdbc = jdbc;
        this.authorsBooksDao = authorsBooksDao;
        this.booksGenreDao = booksGenreDao;
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books(title) values(:title)", Collections.singletonMap("title", book.getTitle()));
        Long id = findMaxId();
        book.getAuthors().forEach(t -> authorsBooksDao.insertIntoAuthorsBooks(t.getId(), id));
        book.getGenres().forEach(t -> booksGenreDao.insertIntoBooksGenres(id, t.getId()));
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
            book.getAuthors().addAll(findAuthorsByBookId(book.getId()));
            book.getGenres().addAll(findGenresByBookId(book.getId()));
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = jdbc.query("select * from books", new BookMapper());
        books.forEach(t -> {
            t.getAuthors().addAll(findAuthorsByBookId(t.getId()));
            t.getGenres().addAll(findGenresByBookId(t.getId()));
        });
        return books;
    }

    @Override
    public void addAuthorByBookId(Long bookId, Long authorId) {
        authorsBooksDao.insertIntoAuthorsBooks(authorId, bookId);
    }

    @Override
    public void deleteAuthorByBookId(Long bookId, Long authorId) {
        authorsBooksDao.deleteFromAuthorsBooks(authorId, bookId);
    }

    @Override
    public void addGenreByBookId(Long bookId, Long genreId) {
        booksGenreDao.insertIntoBooksGenres(bookId, genreId);
    }

    @Override
    public void deleteGenreByBookId(Long bookId, Long genreId) {
        booksGenreDao.deleteFromBooksGenres(bookId, genreId);
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from books where id = :id", Collections.singletonMap("id", id));
        authorsBooksDao.deleteByBookId(id);
        booksGenreDao.deleteByBookId(id);
    }

    private List<Author> findAuthorsByBookId(Long id) {
        List<AuthorsBooks> authorsBooks = jdbc.query("select * from authors_books where id_book = :id", Collections.singletonMap("id", id), new AuthorBookMapper());
        List<Long> authorsIds = new ArrayList<>();
        authorsBooks.forEach(t -> authorsIds.add(t.getIdAuthor()));
        return jdbc.query("select * from authors where id in (:ids)", Collections.singletonMap("ids", authorsIds), new AuthorMapper());
    }

    private List<Genre> findGenresByBookId(Long id) {
        List<BooksGenres> booksGenres = jdbc.query("select * from books_genres where id_book = :id", Collections.singletonMap("id", id), new BookGenreMapper());
        List<Long> genresIds = new ArrayList<>();
        booksGenres.forEach(t -> genresIds.add(t.getIdGenre()));
        return jdbc.query("select * from genres where id in (:ids)", Collections.singletonMap("ids", genresIds), new GenreMapper());
    }
}
