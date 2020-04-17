package homework2.dao;

import homework2.domain.Author;
import homework2.domain.AuthorsBooks;
import homework2.domain.Book;
import homework2.mapper.AuthorBookMapper;
import homework2.mapper.AuthorMapper;
import homework2.mapper.BookMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorsBooksDao authorsBooksDao;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorsBooksDao authorsBooksDao) {
        this.jdbc = jdbc;
        this.authorsBooksDao = authorsBooksDao;
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> authorParams = new HashMap<>();
        authorParams.put("name", author.getName());
        jdbc.update("insert into authors(name) values(:name)", authorParams);
        Long id = findMaxId();
        author.getBooks().forEach(book -> authorsBooksDao.insertIntoAuthorsBooks(id, book.getId()));
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
        List<AuthorsBooks> authorsBooks = jdbc.query("select * from authors_books where id_author = :id", Collections.singletonMap("id", id), new AuthorBookMapper());
        List<Long> booksIds = new ArrayList<>();
        authorsBooks.forEach(t -> booksIds.add(t.getIdBook()));
        return jdbc.query("select * from books where id in (:ids)", Collections.singletonMap("ids", booksIds), new BookMapper());
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from authors where id = :id", Collections.singletonMap("id", id));
        authorsBooksDao.deleteByAuthorId(id);
    }

    @Override
    public void addBookByAuthorId(Long authorId, Long bookId) {
        authorsBooksDao.insertIntoAuthorsBooks(authorId, bookId);
    }

    @Override
    public void deleteBookByAuthorId(Long authorId, Long bookId) {
        authorsBooksDao.deleteFromAuthorsBooks(authorId, bookId);
    }
}
