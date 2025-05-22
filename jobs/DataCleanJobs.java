package cz.mawalgar.services.jobs;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cz.mawalgar.services.dao.AuthUserDao;
import cz.mawalgar.services.util.log.Logged;

@Startup
@Singleton
public class DataCleanJobs {

    private static final Logger LOG = LogManager.getLogger(DataCleanJobs.class);

    @Inject
    private AuthUserDao dao;

    /**
     * Resets users attempts
     */
    @Schedule(second = "10", minute = "10", hour = "*/23", persistent = false)
    @Logged
    public void resetUsersAttempts() {
      try {
        final Integer results = this.dao.resetAllUsersLayoutSettingsAttempts();
        LOG.info("resetUsersAttempts = " + results);
      } catch (final Exception e) {
        LOG.error(e.getMessage(), e);
      }
    }

}
