package homework2.dao;

import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Transactional
@Repository
public class BookDaoJdbc implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Book book) {
        if (book.getId() == null || book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public void update(Book book) {
        Query query = em.createQuery("update Book b " +
                "set b.title = :title " +
                "where b.id = :id");
        query.setParameter("id", book.getId());
        query.setParameter("title", book.getTitle());
        query.executeUpdate();
    }

    @Override
    public Book getById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
//        List<Book> books = em.createQuery("select b from Book b join fetch b.authors", Book.class)
//                .getResultList();
        return em.createQuery("select b from Book b join fetch b.genres ", Book.class)//where b in :b
//                .setParameter("b", books)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void addAuthorByBookId(Long bookId, Long authorId) {
        Query query = em.createQuery("select a from Author a where a.id = :id");
        query.setParameter("id", authorId);
        Book book = getById(bookId);
//        book.getAuthors().add((Author) query.getSingleResult());
        save(book);
    }

    @Override
    public void deleteAuthorByBookId(Long bookId, Long authorId) {
        Book book = getById(bookId);
//        book.getAuthors().removeIf(t -> t.getId().equals(authorId));
        save(book);
    }

    @Override
    public void addGenreByBookId(Long bookId, Long genreId) {
        Query query = em.createQuery("select g from Genre g where g.id = :id");
        query.setParameter("id", genreId);
        Book book = getById(bookId);
        book.getGenres().add((Genre) query.getSingleResult());
        save(book);
    }

    @Override
    public void deleteGenreByBookId(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().removeIf(t -> t.getId().equals(genreId));
        save(book);
    }
}
