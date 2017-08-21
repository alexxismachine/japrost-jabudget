package de.japrost.jabudget.service;

import java.util.ArrayList;
import java.util.List;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * Business service for {@link Account}s.
 */
public class AccountService {

	private final AccountRepository accountRepository;

	/**
	 * Initalize with all dependencies.
	 * 
	 * @param accountRepository the {@link AccountRepository} to use.
	 */
	public AccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/**
	 * Create a new {@link Account}.
	 * 
	 * @param account the Account to be created
	 * @return the new Account.
	 */
	public Account create(final Account account) {
		// TODO throw a business exception on duplicate account
		return accountRepository.create(account);
	}

	/**
	 * Retrieve all accounts.
	 * 
	 * @return all available accounts.
	 */
	public List<Account> retrieveAll() {
		return new ArrayList<>(accountRepository.findAll());
	}

	/**
	 * Retrieve a single {@link Account} by its id.
	 * 
	 * @param id the id of the {@link Account}.
	 * @return the found {@link Account}.
	 */
	public Account retrieveById(final String id) {
		// TODO throw a business exception on missing account
		return accountRepository.findById(id).get();
	}
}
