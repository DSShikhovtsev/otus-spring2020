package homework2.dao;

import homework2.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book, Long> {

    @Query(value = "insert into authors_books(id_author, id_book) " +
            "values(:authorId, :bookId)", nativeQuery = true)
    void addAuthorToBook(Long bookId, Long authorId);

    @Query(value = "delete from authors_books ab " +
            "where ab.id_author = :authorId and ab.id_book = :bookId", nativeQuery = true)
    void deleteAuthorFromBook(Long bookId, Long authorId);

    @Query(value = "insert into books_genres(id_book, id_genre) " +
            "values(:bookId, :genreId)", nativeQuery = true)
    void addGenreToBook(Long bookId, Long genreId);

    @Query(value = "delete from books_genres bg " +
            "where bg.id_book = :bookId and bg.id_genre = :genreId", nativeQuery = true)
    void deleteGenreFromBook(Long bookId, Long genreId);
}
