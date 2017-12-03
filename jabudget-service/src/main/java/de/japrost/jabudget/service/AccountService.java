package de.japrost.jabudget.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * Business service for {@link Account}s.
 */
public class AccountService {

	private final AccountRepository accountRepository;

	/**
	 * Initialize with all dependencies.
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
	 * @throws DomainException when the Account already exists.
	 */
	public Account create(final Account account) throws DomainException {
		return accountRepository.create(account);
	}

	/**
	 * Update an existing {@link Account}.
	 * 
	 * @param account the Account to be updated
	 * @return the updated Account.
	 * @throws DomainException when the Account does not exist.
	 */
	public Account update(final Account account) throws DomainException {
		return accountRepository.update(account);
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
	 * @return the {@link Account} with the id. An empty {@link Optional} if no {@link Account} is available for the given
	 *         id.
	 */
	public Optional<Account> retrieveById(final String id) {
		return accountRepository.findById(id);
	}

	/**
	 * Delete a single {@link Account} by its id.
	 * 
	 * @param id of the {@link Account} to delete.
	 * @return {@link Boolean#TRUE} if the account is removed after this operation.
	 */
	public Boolean erase(final String id) {
		return accountRepository.delete(id);
	}

}
