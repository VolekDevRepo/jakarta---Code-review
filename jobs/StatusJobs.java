package cz.mawalgar.services.jobs;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cz.mawalgar.services.dao.MawaCharactersDao;
import cz.mawalgar.services.dao.PageViewsDao;
import cz.mawalgar.services.dao.dto.ServerStatus;
import cz.mawalgar.services.server.MawalgarPing;
import cz.mawalgar.services.util.log.Logged;

/**
 * @author morty
 *         29. 4. 2020
 *         EnergyCloud a.s.
 */
@Startup
@Singleton
public class StatusJobs {

  private static final Logger LOG = LogManager.getLogger(StatusJobs.class);

  @Inject
  private MawalgarPing mawalgarPing;

  @Inject
  private MawaCharactersDao mawaCharactersDao;

  @Inject
  private PageViewsDao pageViewsDao;

  @Inject
  private ServerStatus serverStatus;

  /**
   * Ping to server
   */
  @Schedule(second = "10", minute = "*/1", hour = "*", persistent = false)
  @Logged
  public void pingToAuthServer() {
    try {
      this.serverStatus.setAuthServerStatus(this.mawalgarPing.hostAvailabilityCheck("0.0.0.0", 8085));
    } catch (final Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }


  /**
   * Fetch all characters info.
   */
  @Schedule(second = "20", minute = "*/1", hour = "*", persistent = false)
  @Logged
  public void getAllCharacters() {
    try {
      this.serverStatus.setAllCharacters(this.mawaCharactersDao.getAllCharacters());
    } catch (final Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  /**
   * Fetch all characters info.
   */
  @Schedule(second = "50", minute = "*/1", hour = "*", persistent = false)
  @Logged
  public void updatePageView() {
    try {
      if (this.serverStatus.getPageView() == null || this.serverStatus.getPageView() == null || this.serverStatus.getPageView().getViewsCount() == 0) {
        return;
      }
      this.pageViewsDao.updatePageView(this.serverStatus.getPageView());
    } catch (final Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

}
