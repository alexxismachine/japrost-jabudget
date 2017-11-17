package de.japrost.jabudget.repository;

import java.util.Optional;
import java.util.Set;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;

/**
 * The repository for {@link Account}s,
 */
public interface AccountRepository {

	/**
	 * Create a new {@link Account} with the given values.
	 * 
	 * @param account the {@link Account} to create.
	 * @return The account as stored in the repository.
	 * @throws DomainException with {@link DomainFailure#DUPLICATE_ENTITY}
	 */
	Account create(Account account) throws DomainException;

	/**
	 * Update an existing {@link Account}.
	 * 
	 * @param account the {@link Account} to update.
	 */
	// TODO what on not found?
	void update(Account account);

	/**
	 * Find an {@link Account} by id.
	 * 
	 * @param id the {@link Account} id.
	 * @return the {@link Account} with the id. An empty {@link Optional} if no {@link Account} is available for the given
	 *         id.
	 */
	Optional<Account> findById(String id);

	/**
	 * Find all {@link Account}s.
	 * 
	 * @return all available {@link Account}s.
	 */
	Set<Account> findAll();

	/**
	 * Delete an {@link Account}.
	 * 
	 * @param account the {@link Account} to delete.
	 */
	// TODO what on not found?
	void delete(Account account);
}
