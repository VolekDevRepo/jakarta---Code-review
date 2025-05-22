package cz.mawalgar.services.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import cz.mawalgar.services.dao.en.IpView;
import cz.mawalgar.services.util.log.LogLevel;
import cz.mawalgar.services.util.log.Logged;

/**
 * @author Martin Volek
 * Created: 13.06.2018
 */
@Named
@RequestScoped
public class IpViewsDao {

  @PersistenceContext(unitName = "mawa-web-jta")
  private EntityManager manager;


  @Transactional(value = Transactional.TxType.REQUIRED)
  @Logged(level = LogLevel.DEBUG)
  public IpView createIpView(final IpView ipView) {
    Objects.requireNonNull(ipView, "Parameter ipView cannot be null.");

    this.manager.persist(ipView);
    this.manager.flush();
    return ipView;
  }

  @Transactional(value = Transactional.TxType.REQUIRED)
  @Logged(level = LogLevel.DEBUG)
  public List<IpView> getIpViews() {
    final String qlString = "select I from IpView I";
    final TypedQuery<IpView> query = this.manager.createQuery(qlString, IpView.class);
    final List<IpView> ipViews  = query.getResultList();
    return ipViews;
  }
}
