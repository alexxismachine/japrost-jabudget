package de.japrost.jabudget.domain.fixtures.account;

import de.japrost.jabudget.domain.account.Account;

/**
 * Fixtures for {@link Account}s.
 */
public class AccountFixtures implements AccountFixtureValues {

	/**
	 * Create the default Account.
	 *
	 * @return the default account.
	 */
	public Account createDefault() {
		return createDefaultBuilder().build();
	}

	/**
	 * Create the default Account.Builder.
	 *
	 * @return the default account builder.
	 */
	public Account.Builder createDefaultBuilder() {
		return Account.Builder.builder(ACCOUNT_DEF_ID).setName(ACCOUNT_DEF_NAME);
	}

	/**
	 * Create the alternate Account.
	 *
	 * @return the alternate account.
	 */
	public Account createAlternate() {
		return createAlternateBuilder().build();
	}

	/**
	 * Create the alternate Account.Builder.
	 *
	 * @return the alternate account builder.
	 */
	public Account.Builder createAlternateBuilder() {
		return Account.Builder.builder(ACCOUNT_ALT_ID).setName(ACCOUNT_ALT_NAME);
	}
}
