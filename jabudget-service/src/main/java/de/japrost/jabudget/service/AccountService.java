package de.japrost.jabudget.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.account.Entry;
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
		// TODO the service creates the ID. Do not use create to import an existing account (e.g. created in an offline application).
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

	/**
	 * Create a new {@link Entry} with the given values.
	 *
	 * @param entry the entry to create.
	 * @return The entry as stored in the repository.
	 * @throws DomainException with {@link DomainFailure#DUPLICATE_ENTITY} if the given entry already exists.
	 * @throws DomainException with {@link DomainFailure#MISSING_ENTITY_REFERENCE} if the account for the entry does not exists.
	 */
	public Entry create(final Entry entry) throws DomainException {
		// TODO who creates the Entry-Code.
		return accountRepository.create(entry);
	}

}
