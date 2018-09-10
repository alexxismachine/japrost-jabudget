/**
 *
 */
package de.japrost.jabudget.service;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtures;
import de.japrost.jabudget.repository.AccountRepository;
import de.japrost.jabudget.serialization.DomainStore;
import de.japrost.jabudget.serialization.Serialization;

/**
 * Test the {@link SerializationService}.
 */
public class SerializationServiceTest {

	private SerializationService cut;
	private Serialization serialization;
	private AccountRepository accountRepository;
	private final AccountFixtures accountFixtures = new AccountFixtures();

	/**
	 */
	@Before
	public void setUp() {
		serialization = Mockito.mock(Serialization.class);
		accountRepository = Mockito.mock(AccountRepository.class);
		cut = new SerializationService(serialization, accountRepository);
	}

	/**
	 * Delegate finding all values and passing them to serialization.
	 */
	@Test
	public void serializeDelegates() {
		// given
		Mockito.when(accountRepository.findAll()).thenReturn(Set.of(accountFixtures.createDefault()));
		// when
		cut.serialize();
		// then
		Mockito.verify(serialization).serialize(Mockito.any());
	}

	/**
	 * Delegate loading all values and passing them for replacement.
	 */
	@Test
	public void deserializeDelegates() {
		// given
		// TODO use fixture
		final DomainStore domainStore = new DomainStore();
		final Set<Account> accounts = Set.of(accountFixtures.createDefault());
		domainStore.setAccounts(accounts);
		Mockito.when(serialization.deserialize()).thenReturn(domainStore);
		// when
		cut.deserialize();
		// then
		Mockito.verify(accountRepository).replaceAll(accounts);
	}
}
