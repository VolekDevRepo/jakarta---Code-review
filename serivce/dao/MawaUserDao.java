package cz.mawalgar.services.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cz.mawalgar.services.dao.en.AuthUser;
import cz.mawalgar.services.dao.en.MawaUser;
import cz.mawalgar.services.dao.to.AuthUserTO;
import cz.mawalgar.services.encrypt.ShaEncrypt;
import cz.mawalgar.services.exceptions.UserNotFoundException;
import cz.mawalgar.services.util.log.LogLevel;
import cz.mawalgar.services.util.log.Logged;

/**
 * Dao for the {@link AuthUser} domain model.
 *
 * @author Martin Volek
 *         Created: 24.07.2018
 */
@Named
@RequestScoped
public class MawaUserDao {

  @PersistenceContext(unitName = "mawa-realmd-jta")
  private EntityManager entityManager;

  private static final Logger LOG = LogManager.getLogger(MawaUserDao.class);

  /**
   * Gets user ID by username.
   *
   * @param username the username
   * @return user ID by username
   */
  @Transactional(value = Transactional.TxType.SUPPORTS)
  @Logged(level = LogLevel.DEBUG)
  public Integer getUserIdByUsername(final String username) {
    Objects.requireNonNull(username, "Username cannot be null.");

    final Query query = this.entityManager
        .createQuery("Select U.id from " + MawaUser.class.getSimpleName() + " U where U.username = :username");
    query.setParameter("username", username);

    try {
      return (Integer) query.getSingleResult();
    } catch (final NoResultException e) {
      LOG.error("User by username={} was not found", username);
    }

    return null;
  }


  /**
   * Gets user by ID.
   *
   * @param userId user ID
   * @return user{@link MawaUser}
   */
  @Transactional(value = Transactional.TxType.SUPPORTS)
  @Logged(level = LogLevel.DEBUG)
  public MawaUser getUserById(final Integer userId) {
    Objects.requireNonNull(userId, "UserId cannot be null.");

    return this.entityManager.find(MawaUser.class, userId);
  }


  /**
   * Gets {@link MawaUser} by username.
   *
   * @param username the username
   * @return user record by username
   */
  @Transactional(value = Transactional.TxType.SUPPORTS)
  @Logged(level = LogLevel.DEBUG)
  public MawaUser getUserByUsername(final String username) {
    Objects.requireNonNull(username, "Username cannot be null.");

    final Query query = this.entityManager
        .createQuery("Select U from " + MawaUser.class.getSimpleName() + " U where U.username = :username");
    query.setParameter("username", username);

    try {
      return (MawaUser) query.getSingleResult();
    } catch (final NoResultException e) {
      LOG.error("User by username={} was not found", username);
    }

    return null;
  }


  /**
   * Getting all {@link MawaUser} records.
   *
   * @return list of all {@link MawaUser} records
   */
  @Transactional(value = Transactional.TxType.SUPPORTS)
  @Logged(level = LogLevel.DEBUG)
  public List<MawaUser> getAllUsers() {
    return this.entityManager.createQuery("Select U from " + MawaUser.class.getSimpleName() + " U").getResultList();
  }


  /**
   * Creates {@link MawaUser} record.
   *
   * @param authUser {@link AuthUser}
   * @param authUserTo {@link AuthUserTO}
   */
  @Transactional(value = Transactional.TxType.REQUIRES_NEW)
  @Logged(level = LogLevel.DEBUG)
  public void createMawaUser(final AuthUser authUser, final AuthUserTO authUserTo) {
    final MawaUser mawaUser = new MawaUser();
    final ShaEncrypt passwordSha = new ShaEncrypt();
    final String passwdHash = passwordSha
        .sha1Encrypt(authUser.getUsername().toUpperCase() + ":" + authUserTo.getPassword1().toUpperCase());
    mawaUser.setId(authUser.getId());
    mawaUser.setUsername(authUser.getUsername());
    mawaUser.setSha_pass_hash(passwdHash);
    mawaUser.setSessionkey("");
    mawaUser.setV("");
    mawaUser.setS("");
    mawaUser.setTotp_secret(null);
    mawaUser.setEmail(authUser.getEmail());
    mawaUser.setReg_mail(authUser.getEmail());
    mawaUser.setJoindate(LocalDateTime.now());
    mawaUser.setLast_ip("127.0.0.1");
    mawaUser.setLast_attempt_ip("127.0.0.0");
    mawaUser.setFailed_logins(0);
    mawaUser.setLocked(1);
    mawaUser.setLock_country("00");
    mawaUser.setLast_login(null);
    mawaUser.setOnline(0);
    // Wotlk
    mawaUser.setExpansion(2);
    mawaUser.setMutetime(BigInteger.valueOf(0));
    mawaUser.setMutereason("");
    mawaUser.setMuteby("");
    // Session things has default value
    // English
    mawaUser.setLocale(0);
    mawaUser.setOs("");
    mawaUser.setRecruiter(0);

    this.entityManager.persist(mawaUser);
    this.entityManager.flush();
  }


  /**
   * Unlocks wow account by web account activation.
   *
   * @param username {@link String}
   */
  @Transactional(value = Transactional.TxType.REQUIRES_NEW)
  @Logged(level = LogLevel.DEBUG)
  public void activateWowAccount(final String username) {

    if (username == null) {
      LOG.info("Activate WOW ACCOUNT -- USER ID IS NULL");
      return;
    }
    final MawaUser mawaUser = this.getUserByUsername(username);
    if (mawaUser == null) {
      LOG.info("Activate WOW ACCOUNT  -- MAWAUSER not found");
    }
    mawaUser.setLocked(0);
    this.entityManager.persist(mawaUser);
    this.entityManager.flush();
  }


  /**
   * Resets the password of the user identified by the actionToken.
   *
   * @param user the user
   * @throws UserNotFoundException if the user could not be found
   */
  @Transactional(value = TxType.REQUIRES_NEW)
  @Logged(level = LogLevel.DEBUG)
  public void resetWowPassword(final AuthUser user, final String plainPassword) throws UserNotFoundException {
    // We have to find the user because we use em.merge(user)
    final MawaUser foundUser = this.getUserByUsername(user.getUsername());
    if (foundUser == null) {
      LOG.error("Error resetting password. User not found {}.", user.getUsername());
      throw new UserNotFoundException("Error resetting password. User not found " + user.getUsername());
    }

    final ShaEncrypt passwordSha = new ShaEncrypt();
    final String passwdHash = passwordSha
        .sha1Encrypt(user.getUsername().toUpperCase() + ":" + plainPassword.toUpperCase());
    foundUser.setSessionkey("");
    foundUser.setV("");
    foundUser.setS("");
    foundUser.setSha_pass_hash(passwdHash);
    this.entityManager.persist(foundUser);
    this.entityManager.flush();
    LOG.info("Reset password for user " + user.getUsername());
  }

}
