package de.japrost.jabudget.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtureValues;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtures;
import de.japrost.jabudget.rest.converter.Account2AccountResConverter;
import de.japrost.jabudget.rest.converter.AccountRes2AccountConverter;
import de.japrost.jabudget.rest.model.AccountRes;
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
	@BeforeEach
	public void setUp() {
		accountService = Mockito.mock(AccountService.class);
		cut = new AccountController(accountService, new AccountRes2AccountConverter(), new Account2AccountResConverter());
	}

	/**
	 * Simple delegation.
	 */
	@Test
	void retrieveAllDelegates() {
		// given
		// TODO add some objects.
		final List<Account> result = new ArrayList<>();
		Mockito.when(accountService.retrieveAll()).thenReturn(result);
		// when
		final List<AccountRes> actual = cut.retrieveAll();
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
	void retrieveByIdDelegates() throws DomainException {
		// given
		final Account account = accountFixtures.createDefault();
		Mockito.when(accountService.retrieveById(AccountFixtureValues.ACCOUNT_DEF_ID)).thenReturn(Optional.of(account));
		// when
		final AccountRes actual = cut.retrieveById(AccountFixtureValues.ACCOUNT_DEF_ID);
		//then
		// TODO use a comparison not equal for domain objects.
		assertThat(actual.getId()).isEqualTo(account.getId());
		assertThat(actual.getName()).isEqualTo(account.getName());
	}

	/**
	 * Fail on missing entity.
	 *
	 * @throws DomainException never
	 */
	@Test
	void retrieveByIdFailsOnMissingEntity() throws DomainException {
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
	void createDelegates() throws DomainException {
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
	void updateDelegates() throws DomainException {
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
	void deleteDelegates() {
		// given
		// when
		cut.delete(AccountFixtureValues.ACCOUNT_DEF_ID);
		//then
		Mockito.verify(accountService).erase(AccountFixtureValues.ACCOUNT_DEF_ID);
	}
}