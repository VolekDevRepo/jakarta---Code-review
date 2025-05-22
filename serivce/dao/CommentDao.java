package cz.mawalgar.services.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cz.mawalgar.services.dao.en.AuthGroup;
import cz.mawalgar.services.dao.en.AuthRole;
import cz.mawalgar.services.dao.en.AuthUserGroup;
import cz.mawalgar.services.dao.en.Comment;
import cz.mawalgar.services.dao.en.News;
import cz.mawalgar.services.util.log.LogLevel;
import cz.mawalgar.services.util.log.Logged;

@Named
@RequestScoped
public class CommentDao {

  @PersistenceContext(unitName = "mawa-web-jta")
  private EntityManager entityManager;


  private static final Logger LOG = LogManager.getLogger(CommentDao.class);


  @Transactional(value = Transactional.TxType.SUPPORTS)
  @Logged(level = LogLevel.DEBUG)
  public List<Comment> getCommentsByNewsId(final Integer newsId) {
    Objects.requireNonNull(newsId, "News ID is null.");

    final String qlString = "select C from " + Comment.class.getSimpleName() + " C where C.newsId = :newsId";
    final TypedQuery<Comment> query = this.entityManager.createQuery(qlString, Comment.class);
    query.setParameter("newsId", newsId);
    return query.getResultList();
  }

  @Transactional(value = TxType.REQUIRED)
  @Logged(level = LogLevel.DEBUG)
  public Comment createComment(final Comment comment) {
    Objects.requireNonNull(comment, "Parameter comment cannot be null.");
    this.entityManager.persist(comment);
    this.entityManager.flush();
    return comment;
  }

  @Transactional(value = TxType.REQUIRED)
  public void editComment(final Comment comment) {
    Objects.requireNonNull(comment, "Comment cannot be null.");
    this.entityManager.merge(comment);
    this.entityManager.flush();
  }

  @Transactional(value = Transactional.TxType.SUPPORTS)
  @Logged(level = LogLevel.DEBUG)
  public Comment getCommentById(final Integer commentId) {
    Objects.requireNonNull(commentId, "Comment id cannot be null.");
    return this.entityManager.find(Comment.class, commentId);
  }

  @Transactional(value = TxType.REQUIRED)
  @Logged(level = LogLevel.DEBUG)
  public void delete(final Comment comment) {
    Objects.requireNonNull(comment, "delete method's parameter is mandatory!");
    if (this.entityManager.contains(comment)) {
      this.entityManager.remove(comment);
    } else {
      final Comment foundComment = this.entityManager.find(Comment.class, comment.getId());
      if (foundComment != null) {
        this.entityManager.remove(foundComment);
      }
    }
    this.entityManager.flush();
  }

}
