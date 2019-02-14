package de.japrost.jabudget.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtureValues;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtures;
import de.japrost.jabudget.service.AccountService;

/**
 * Test the {@link AccountController}.
 */
public class AccountControllerTest {

	private AccountController cut;
	private AccountService accountService;
	private final AccountFixtures accountFixtures = new AccountFixtures();

	/**
	 * Set up each test.
	 */
	@Before
	public void setUp() {
		accountService = Mockito.mock(AccountService.class);
		cut = new AccountController(accountService);
	}

	/**
	 * Simple delegation.
	 */
	@Test
	public void retrieveAllDelegates() {
		// given
		final List<Account> result = new ArrayList<>();
		Mockito.when(accountService.retrieveAll()).thenReturn(result);
		// when
		final List<Account> actual = cut.retrieveAll();
		//then
		// TODO use a comparison not equal for domain objects.
		Assertions.assertThat(actual).isEqualTo(result);
	}

	/**
	 * Simple delegation.
	 *
	 * @throws DomainException never
	 */
	@Test
	public void retrieveByIdDelegates() throws DomainException {
		// given
		final Account account = accountFixtures.createDefault();
		Mockito.when(accountService.retrieveById(AccountFixtureValues.ACCOUNT_DEF_ID)).thenReturn(Optional.of(account));
		// when
		final Account actual = cut.retrieveById(AccountFixtureValues.ACCOUNT_DEF_ID);
		//then
		// TODO use a comparison not equal for domain objects.
		Assertions.assertThat(actual).isEqualTo(account);
	}

	/**
	 * Fail on missing entity.
	 *
	 * @throws DomainException never
	 */
	@Test
	public void retrieveByIdFailsOnMissingEntity() throws DomainException {
		// given
		Mockito.when(accountService.retrieveById(AccountFixtureValues.ACCOUNT_DEF_ID))
				.thenReturn(Optional.ofNullable(null));
		// when
		final Throwable thrown = Assertions.catchThrowable(() -> {
			cut.retrieveById(AccountFixtureValues.ACCOUNT_DEF_ID);
		});
		//then
		Assertions.assertThat(thrown).isInstanceOf(DomainException.class);
		// TODO create Assertions for DomainException
		Assertions.assertThat(((DomainException) thrown).getFailure()).isEqualTo(DomainFailure.ENTITY_NOT_AVAILABLE);
	}

	/**
	 * Simple delegation.
	 *
	 * @throws DomainException never
	 */
	@Test
	public void createDelegates() throws DomainException {
		// given
		final Account.Builder builder = accountFixtures.createDefaultBuilder();
		final Account result = builder.build();
		Mockito.when(accountService.create(Mockito.any(Account.class))).thenReturn(result);
		// when
		final Account actual = cut.create(builder);
		//then
		// TODO use a comparison not equal for domain objects.
		Assertions.assertThat(actual).isEqualTo(result);
	}

	/**
	 * Delegation with setting id.
	 *
	 * @throws DomainException never
	 */
	@Test
	public void updateDelegates() throws DomainException {
		// given
		final Account.Builder builder = accountFixtures.createAlternateBuilder();
		final Account result = accountFixtures.createDefault();
		Mockito.when(accountService.update(Mockito.any())).thenReturn(result);
		// when
		final Account actual = cut.update(AccountFixtureValues.ACCOUNT_DEF_ID, builder);
		//then
		// TODO use a comparison not equal for domain objects.
		Assertions.assertThat(actual).isEqualTo(result);
	}

	/**
	 * Simple delegation.
	 */
	@Test
	public void deleteDelegates() {
		// given
		// when
		cut.delete(AccountFixtureValues.ACCOUNT_DEF_ID);
		//then
		Mockito.verify(accountService).erase(AccountFixtureValues.ACCOUNT_DEF_ID);
	}
}