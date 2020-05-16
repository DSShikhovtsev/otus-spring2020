package homework2.dao;

import homework2.domain.Book;
import homework2.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentDaoJdbc implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addComment(Long bookId, Comment comment) {
        comment.setBook(em.find(Book.class, bookId));
        em.persist(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Query query = em.createQuery("delete from Comment c where c.id = :commentId");
        query.setParameter("commentId", commentId);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }
}
