package de.japrost.jabudget.repository.inmemory;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
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
	 * 
	 * @throws Exception never
	 */
	@Test
	public void create_doesNotReturnGivenInstance() throws Exception {
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
	 * 
	 * @throws Exception never
	 */
	@Test
	public void create_doesNotStoreGivenInstance() throws Exception {
		// given
		Account account = Account.Builder.builder("an id").build();
		// when
		cut.create(account);
		account.setName("a name");
		// then 
		Account actual = cut.findById("an id").get();
		assertThat(actual.getName()).isNull();
	}

	/**
	 * Duplicate throws Exception.
	 * 
	 * @throws Exception never
	 */
	@Test
	public void create_doesNotStoreDuplicates() throws Exception {
		// given
		Account account = Account.Builder.builder("an id").build();
		cut.create(account);
		// when then
		Assertions.assertThatExceptionOfType(DomainException.class).isThrownBy(() -> {
			cut.create(account);
		}).matches(p -> p.getFailure() == DomainFailure.DUPLICATE_ENTITY);
	}
}
