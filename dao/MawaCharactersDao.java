package cz.mawalgar.services.dao;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cz.mawalgar.services.dao.en.MawaCharacter;
import cz.mawalgar.services.util.log.LogLevel;
import cz.mawalgar.services.util.log.Logged;

@Named
@RequestScoped
public class MawaCharactersDao {

	@PersistenceContext(unitName = "mawa-char-jta")
	private EntityManager entityManager;

	private static final Logger LOG = LogManager.getLogger(MawaCharactersDao.class);

	/**
	 * Getting all {@link MawaCharacter} records.
	 *
	 * @return list of all {@link MawaCharacter} records
	 */
	@Transactional(value = Transactional.TxType.SUPPORTS)
	@Logged(level = LogLevel.DEBUG)
	public List<MawaCharacter> getAllCharacters() {
		final TypedQuery<MawaCharacter> query = this.entityManager
				.createQuery("Select CH from " + MawaCharacter.class.getSimpleName() + " CH", MawaCharacter.class);
		return query.getResultList();
	}


	@Transactional(value = Transactional.TxType.SUPPORTS)
	@Logged(level = LogLevel.DEBUG)
	public Integer getCountOfAllCharacters() {

		final String qlString = "Select count(CH) from " + Integer.class.getSimpleName() + " CH"//
				+ " where CH.online = true";

		final TypedQuery<Integer> query = this.entityManager.createQuery(qlString, Integer.class);
		return query.getSingleResult();
	}

	/**
	 * Gets {@link MawaCharacter}s by account.
	 *
	 * @param account the account
	 * @return character record by account
	 */
	@Transactional(value = Transactional.TxType.SUPPORTS)
	@Logged(level = LogLevel.DEBUG)
	public List<MawaCharacter> getCharacterByAccountId(final Integer account) {
		Objects.requireNonNull(account, "Username cannot be null.");

		final TypedQuery<MawaCharacter> query = this.entityManager.createQuery(
				"Select U from " + MawaCharacter.class.getSimpleName() + " U where U.account = :account",
				MawaCharacter.class);
		query.setParameter("account", account);

		try {
			return query.getResultList();
		} catch (final NoResultException e) {
			LOG.error("Character by account={} was not found", account);
		}

		return Collections.emptyList();
	}




//	/**
//	 * Getting all online {@link MawaCharacter} records.
//	 *
//	 * @return list of all online {@link MawaCharacter} records
//	 */
//	@Transactional(value = Transactional.TxType.SUPPORTS)
//	@Logged(level = LogLevel.DEBUG)
//	public List<MawaCharacter> getAllOnlineCharacters() {
//
//		final String qlString = "Select CH from " + MawaCharacter.class.getSimpleName() + " CH"//
//				+ " where CH.online = true";
//		final TypedQuery<MawaCharacter> query = this.entityManager.createQuery(qlString, MawaCharacter.class);
//
//		try {
//			return query.getResultList();
//		} catch (final NoResultException e) {
//			e.getCause();
//			return Collections.emptyList();
//		}
//	}

}
