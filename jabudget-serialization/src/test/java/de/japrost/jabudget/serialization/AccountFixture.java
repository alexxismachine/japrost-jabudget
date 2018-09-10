package de.japrost.jabudget.serialization;

import java.util.Comparator;

import de.japrost.jabudget.domain.account.Account;

/**
 * Fixtures for {@link Account}s.
 */
public class AccountFixture implements FixtureValues {

	/**
	 * Comparator based on id.
	 */
	public final static Comparator<Account> ACCOUNT_COMPARATOR = new Comparator<>() {

		@Override
		public int compare(final Account arg0, final Account arg1) {
			return arg0.getId().compareTo(arg1.getId());
		}
	};

	/**
	 * @return a simple full blown account.
	 */
	public static Account defaultAccount() {
		final Account result = new Account(DEF_ACCOUNT_ID);
		result.setName(DEF_ACCOUNT_NAME);
		return result;
	}

	/**
	 * @return an alternate full blown account.
	 */
	public static Account alternateAccount() {
		final Account result = new Account(ALT_ACCOUNT_ID);
		result.setName(ALT_ACCOUNT_NAME);
		return result;
	}

}
