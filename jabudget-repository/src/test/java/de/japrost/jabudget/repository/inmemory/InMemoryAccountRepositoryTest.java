package de.japrost.jabudget.repository.inmemory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtureValues;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtures;

/**
 * Test the {@link InMemoryAccountRepository}.
 */
public class InMemoryAccountRepositoryTest implements AccountFixtureValues {

	private InMemoryAccountRepository cut;
	private final AccountFixtures accountFixtures = new AccountFixtures();
	private Account defaultAccount;

	/**
	 * Set up each test.
	 */
	@BeforeEach
	public void setUp() {
		cut = new InMemoryAccountRepository();
		defaultAccount = accountFixtures.createDefault();
	}

	/**
	 * The given instance is not returned when stored.
	 *
	 * @throws Exception never
	 */
	@Test
	 void create_doesNotReturnGivenInstance() throws Exception {
		// given
		// when
		final Account actual = cut.create(defaultAccount);
		// then
		assertThat(actual).isEqualTo(defaultAccount);
		assertThat(actual).isNotSameAs(defaultAccount);
	}

	/**
	 * The given instance is not stored.
	 *
	 * @throws Exception never
	 */
	@Test
	 void create_doesNotStoreGivenInstance() throws Exception {
		// given
		// when
		cut.create(defaultAccount);
		defaultAccount.setName(ACCOUNT_ALT_NAME);
		// then
		final Account actual = cut.findById(ACCOUNT_DEF_ID).get();
		assertThat(actual.getName()).isEqualTo(ACCOUNT_DEF_NAME);
	}

	/**
	 * Duplicate throws Exception.
	 *
	 * @throws Exception never
	 */
	@Test
	 void create_doesNotStoreDuplicates() throws Exception {
		// given
		cut.create(defaultAccount);
		// when then
		Assertions.assertThatExceptionOfType(DomainException.class).isThrownBy(() -> {
			cut.create(defaultAccount);
		}).matches(p -> p.getFailure() == DomainFailure.DUPLICATE_ENTITY);
	}

	/**
	 * Update updates.
	 *
	 * @throws Exception never
	 */
	@Test
	 void update_updates() throws Exception {
		// given
		cut.create(defaultAccount);
		defaultAccount.setName(ACCOUNT_ALT_NAME);
		// when
		cut.update(defaultAccount);
		// then
		final Account actual = cut.findById(ACCOUNT_DEF_ID).get();
		assertThat(actual.getName()).isEqualTo(ACCOUNT_ALT_NAME);
	}

	/**
	 * Missing throws Exception.
	 *
	 * @throws Exception never
	 */
	@Test
	 void update_doesNotStoreMissing() throws Exception {
		// given
		// when then
		Assertions.assertThatExceptionOfType(DomainException.class).isThrownBy(() -> {
			cut.update(defaultAccount);
		}).matches(p -> p.getFailure() == DomainFailure.MISSING_ENTITY);
	}

	/**
	 * Replace all removes all elements and replaces them by the given.
	 *
	 * @throws Exception never
	 */
	@Test
	 void replaceAll_replacesAll() throws Exception {
		// given
		cut.create(defaultAccount);
		// when
		cut.replaceAll(Set.of(accountFixtures.createAlternate()));
		// then
		Assertions.assertThat(cut.findById(ACCOUNT_DEF_ID)).isEmpty();
		Assertions.assertThat(cut.findAll()).hasSize(1);
		Assertions.assertThat(cut.findById(ACCOUNT_ALT_ID)).contains(accountFixtures.createAlternate());
	}
}
