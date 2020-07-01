package batch.reposinories.h2;

public interface BookH2RepositoryCustom {

    void addAuthorToBook(Long bookId, Long authorId);
    void deleteAuthorFromBook(Long bookId, Long authorId);
    void addGenreToBook(Long bookId, Long genreId);
    void deleteGenreFromBook(Long bookId, Long genreId);
}
