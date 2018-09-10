package de.japrost.jabudget.repository;

import java.util.Optional;
import java.util.Set;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;

/**
 * The repository for {@link Account}s.
 */
public interface AccountRepository {

	/**
	 * Create a new {@link Account} with the given values.
	 *
	 * @param account the {@link Account} to create.
	 * @return The account as stored in the repository.
	 * @throws DomainException with {@link DomainFailure#DUPLICATE_ENTITY} if the given account already exists.
	 */
	Account create(Account account) throws DomainException;

	/**
	 * Update an existing {@link Account}.
	 *
	 * @param account the {@link Account} to update.
	 * @return The account as stored in the repository.
	 * @throws DomainException with {@link DomainFailure#MISSING_ENTITY} if the given account does not exist.
	 */
	Account update(Account account) throws DomainException;

	/**
	 * Replace all {@link Account}s with the given. Intended for imports.
	 *
	 * @param accounts the {@link Account}s to fill the repository.
	 */
	void replaceAll(Set<Account> accounts);

	/**
	 * Find all {@link Account}s.
	 *
	 * @return all available {@link Account}s.
	 */
	Set<Account> findAll();

	/**
	 * Find an {@link Account} by id.
	 *
	 * @param id the {@link Account} id.
	 * @return the {@link Account} with the id. An empty {@link Optional} if no {@link Account} is available for the given
	 *         id.
	 */
	Optional<Account> findById(String id);

	/**
	 * Delete an {@link Account} by its id.
	 *
	 * @param accountId the id of the {@link Account} to delete.
	 * @return {@link Boolean#TRUE} if the Account does not exist after this operation. It does not indicate that the
	 *         entity to delete did exist. Returns {@link Boolean#FALSE} if the entity could not be deleted.
	 */
	Boolean delete(String accountId);
}
