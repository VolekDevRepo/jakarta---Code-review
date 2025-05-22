package cz.mawalgar.services.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import cz.mawalgar.services.dao.en.PageView;
import cz.mawalgar.services.util.log.LogLevel;
import cz.mawalgar.services.util.log.Logged;

import java.util.Optional;

/**
 * @author Martin Volek
 * Created: 13.06.2018
 */
@Named
@RequestScoped
public class PageViewsDao {

  @PersistenceContext(unitName = "mawa-web-jta")
  private EntityManager manager;


  @Transactional(value = Transactional.TxType.REQUIRED)
  @Logged(level = LogLevel.DEBUG)
  public void updatePageView(final PageView pageViews) {
    this.manager.merge(pageViews);
    this.manager.flush();
  }

  @Transactional(value = Transactional.TxType.REQUIRED)
  @Logged(level = LogLevel.DEBUG)
  public Optional<PageView> getPageView() {
    final String qlString = "select P from PageView P";
    final TypedQuery<PageView> query = this.manager.createQuery(qlString, PageView.class);
    try {
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
