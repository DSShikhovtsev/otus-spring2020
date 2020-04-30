package homework2.dao;

import homework2.domain.Book;
import homework2.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

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
        return em.createQuery("select b from Book b join fetch b.genres ", Book.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void addGenreByBookId(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().add(em.find(Genre.class, genreId));
        save(book);
    }

    @Override
    public void deleteGenreByBookId(Long bookId, Long genreId) {
        Book book = getById(bookId);
        book.getGenres().removeIf(t -> t.getId().equals(genreId));
        save(book);
    }
}
