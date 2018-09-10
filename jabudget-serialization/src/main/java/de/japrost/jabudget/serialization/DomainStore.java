package de.japrost.jabudget.serialization;

import java.util.Set;

import de.japrost.jabudget.domain.account.Account;

/**
 * The whole domain to store.
 */
public class DomainStore {

	private Set<Account> accounts;

	/**
	 * Get the accounts.
	 *
	 * @return the accounts.
	 */
	public Set<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Set the accounts.
	 *
	 * @param accounts the accounts.
	 */
	public void setAccounts(final Set<Account> accounts) {
		this.accounts = accounts;
	}

}
