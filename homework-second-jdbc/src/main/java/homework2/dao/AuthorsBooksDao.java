package homework2.dao;

public interface AuthorsBooksDao {

    void insertIntoAuthorsBooks(Long authorId, Long bookId);
    void deleteFromAuthorsBooks(Long authorId, Long bookId);
    void deleteByBookId(Long id);
    void deleteByAuthorId(Long id);
}
