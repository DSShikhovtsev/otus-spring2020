package homework2.dao;

import homework2.domain.Author;
import homework2.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Author author) {
        if (author.getId() == null || author.getId() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    @Override
    public void update(Author author) {
        Query query = em.createQuery("update Author a " +
                "set a.name = :name " +
                "where a.id = :id");
        query.setParameter("id", author.getId());
        query.setParameter("name", author.getName());
        query.executeUpdate();
    }

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void addBookByAuthorId(Long authorId, Long bookId) {
        Query query = em.createQuery("select b from Book b where b.id = :id");
        query.setParameter("id", bookId);
        Author author = getById(authorId);
        author.getBooks().add((Book) query.getSingleResult());
        save(author);
    }

    @Override
    public void deleteBookByAuthorId(Long authorId, Long bookId) {
        Author author = getById(authorId);
        author.getBooks().removeIf(t -> t.getId().equals(bookId));
        save(author);
    }
}
