package homework2.dao;

public interface BooksGenreDao {

    void insertIntoBooksGenres(Long bookId, Long genreId);
    void deleteFromBooksGenres(Long bookId, Long genreId);
    void deleteByBookId(Long id);
    void deleteByGenreId(Long id);
}
