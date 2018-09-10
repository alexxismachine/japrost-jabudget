package de.japrost.jabudget.serialization;

import static de.japrost.jabudget.serialization.AccountFixture.alternateAccount;
import static de.japrost.jabudget.serialization.AccountFixture.defaultAccount;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import de.japrost.jabudget.domain.account.Account;

/**
 * Fixtures for {@link DomainStore}s.
 */
public final class DomainStoreFixture  {

	/**
	 * @return a simple full blown domain store.
	 */
	public static DomainStore defaultDomainStore() {
		final DomainStore result = new DomainStore();
		result.setAccounts(ofAccounts(defaultAccount()));
		return result;
	}

	/**
	 * @return an alternate full blown domain store.
	 */
	public static DomainStore alternateDomainStore() {
		final DomainStore result = new DomainStore();
		result.setAccounts(ofAccounts(alternateAccount()));
		return result;
	}

	/**
	 * @return a domain store with multiple entities.
	 */
	public static DomainStore multiDomainStore() {
		final DomainStore result = new DomainStore();
		result.setAccounts(ofAccounts(defaultAccount(), alternateAccount()));
		return result;
	}

	@SafeVarargs
	private static Set<Account> ofAccounts(final Account... t) {
		return of(AccountFixture.ACCOUNT_COMPARATOR, t);
	}

	@SafeVarargs
	private static <T> Set<T> of(final Comparator<T> comparator, final T... t) {
		final TreeSet<T> result = new TreeSet<>(comparator);
		result.addAll(Arrays.asList(t));
		return result;
	}
}
