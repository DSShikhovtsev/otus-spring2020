package homework2.dao;

import homework2.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class GenreDaoJdbc implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Genre genre) {
        if (genre.getId() == null || genre.getId() <= 0) {
            em.persist(genre);
        } else {
            em.merge(genre);
        }
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void update(Genre genre) {
        Query query = em.createQuery("update Genre g " +
                "set g.description = :description " +
                "where g.id = :id");
        query.setParameter("id", genre.getId());
        query.setParameter("description", genre.getDescription());
        query.executeUpdate();
    }
}
