package de.japrost.jabudget.repository.inmemory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import de.japrost.jabudget.domain.account.Account;

/**
 * Test the {@link InMemoryAccountRepository}.
 */
public class InMemoryAccountRepositoryTest {

	private InMemoryAccountRepository cut;

	/**
	 * Set up each test.
	 */
	@Before
	public void setUp() {
		cut = new InMemoryAccountRepository();
	}

	/**
	 * The given instance is not returned when stored.
	 */
	@Test
	public void create_doesNotReturnGivenInstance() {
		// given
		Account account = Account.Builder.builder("an id").build();
		// when
		Account actual = cut.create(account);
		// then 
		assertThat(actual).isEqualTo(account);
		assertThat(actual).isNotSameAs(account);
	}

	/**
	 * The given instance is not stored.
	 */
	@Test
	public void create_doesNotStoreGivenInstance() {
		// given
		Account account = Account.Builder.builder("an id").build();
		// when
		cut.create(account);
		account.setName("a name");
		// then 
		Account actual = cut.findById("an id").get();
		assertThat(actual.getName()).isNull();
	}
}
