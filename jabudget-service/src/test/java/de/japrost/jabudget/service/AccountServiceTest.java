package de.japrost.jabudget.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtureValues;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtures;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * Test the {@link AccountService}.
 */
public class AccountServiceTest implements AccountFixtureValues {

	private AccountService cut;
	private AccountRepository accountRepository;
	private final AccountFixtures accountFixtures = new AccountFixtures();

	/**
	 * Set up each test.
	 *
	 * @throws Exception on failure
	 */
	@Before
	public void setUp() throws Exception {
		accountRepository = Mockito.mock(AccountRepository.class);
		cut = new AccountService(accountRepository);
	}

	/**
	 * Simple delegation.
	 *
	 * @throws DomainException never
	 */
	@Test
	public void createDelegates() throws DomainException {
		final Account expected = accountFixtures.createDefault();
		Mockito.when(accountRepository.create(accountFixtures.createDefault())).thenReturn(expected);
		// when
		final Account actual = cut.create(accountFixtures.createDefault());
		//then
		Assertions.assertThat(actual).isSameAs(expected);
	}

	/**
	 * Simple delegation.
	 *
	 * @throws DomainException never
	 */
	@Test
	public void updateDelegates() throws DomainException {
		final Account expected = accountFixtures.createDefault();
		Mockito.when(accountRepository.update(accountFixtures.createDefault())).thenReturn(expected);
		// when
		final Account actual = cut.update(accountFixtures.createDefault());
		//then
		Assertions.assertThat(actual).isSameAs(expected);
	}

	/**
	 * Simple delegation.
	 */
	@Test
	public void retrieveAllDelegates() {
		final Account expected = accountFixtures.createDefault();
		Mockito.when(accountRepository.findAll()).thenReturn(Set.of(expected));
		// when
		final List<Account> actual = cut.retrieveAll();
		//then
		Assertions.assertThat(actual).containsExactly(expected);
	}

	/**
	 * Simple delegation.
	 */
	@Test
	public void retrieveByIdDelegates() {
		final Account expected = accountFixtures.createDefault();
		Mockito.when(accountRepository.findById(ACCOUNT_DEF_ID)).thenReturn(Optional.of(expected));
		// when
		final Optional<Account> actual = cut.retrieveById(ACCOUNT_DEF_ID);
		//then
		Assertions.assertThat(actual).hasValue(expected);
	}

	/**
	 * Simple delegation.
	 */
	@Test
	public void retrieveEraseDelegates() {
		Mockito.when(accountRepository.delete(ACCOUNT_DEF_ID)).thenReturn(Boolean.TRUE);
		// when
		final Boolean actual = cut.erase(ACCOUNT_DEF_ID);
		//then
		Assertions.assertThat(actual).isTrue();
	}
}
