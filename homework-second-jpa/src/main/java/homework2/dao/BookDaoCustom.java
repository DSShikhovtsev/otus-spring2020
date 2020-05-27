package homework2.dao;

public interface BookDaoCustom {

    void addAuthorToBook(Long bookId, Long authorId);
    void deleteAuthorFromBook(Long bookId, Long authorId);
    void addGenreToBook(Long bookId, Long genreId);
    void deleteGenreFromBook(Long bookId, Long genreId);
}
