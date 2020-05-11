package homework2.dao;

import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BookDaoImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book book = getById(bookId);
        book.getAuthors().add(em.find(Author.class, authorId));
        save(book);
    }

    @Override
    public void deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getById(bookId);
        book.getAuthors().removeIf(t -> t.getId().equals(authorId));
        save(book);
    }

    @Override
    public void addGenreToBook(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().add(em.find(Genre.class, genreId));
        save(book);
    }

    @Override
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().removeIf(t -> t.getId().equals(genreId));
        save(book);
    }

    private void save(Book book) {
        if (book.getId() == null || book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    private Book getById(Long id) {
        return em.find(Book.class, id);
    }
}
