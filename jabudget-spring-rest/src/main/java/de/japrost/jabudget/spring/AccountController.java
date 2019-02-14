package de.japrost.jabudget.spring;

import static de.japrost.jabudget.spring.PathMapping.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.account.Entry;
import de.japrost.jabudget.domain.account.EntryBuilder;
import de.japrost.jabudget.service.AccountService;

/**
 * REST controller for {@link Account}s.
 */
@RestController @RequestMapping(BASE) public class AccountController {

	private final AccountService accountService;

	/**
	 * Instantiate with necessary dependencies.
	 *
	 * @param accountService the {@link AccountService} to use.
	 */
	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Retrieve all accounts.
	 *
	 * @return all accounts
	 */
	@GetMapping(ACCOUNTS)
	public List<Account> retrieveAll() {
		return accountService.retrieveAll();
	}

	/**
	 * Retrieve a single {@link Account} by id.
	 *
	 * @param id the id to look for
	 * @return the found {@link Account}
	 * @throws DomainException if finding fails
	 */
	@GetMapping(ACCOUNTS_ID)
	public Account retrieveById(@PathVariable(ID_PARAM) final String id) throws DomainException {
		final Optional<Account> account = accountService.retrieveById(id);
		// TODO do not use DomainException in Controller.
		return account.orElseThrow(() -> new DomainException(DomainFailure.ENTITY_NOT_AVAILABLE));
	}

	/**
	 * Create a new Account.
	 *
	 * @param account the account to create
	 * @return the new account
	 * @throws DomainException if creating fails
	 */
	@PostMapping(path = ACCOUNTS, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account create(@RequestBody final Account.Builder account) throws DomainException {
		return accountService.create(account.build());
	}

	/**
	 * Update an existing Account.
	 *
	 * @param id      the id to look for
	 * @param account the account to update. The id from the account is ignored.
	 * @return the updated account
	 * @throws DomainException if creating fails
	 */
	@PutMapping(path = ACCOUNTS_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
	// TODO use HTTP UPDATE?
	public Account update(@PathVariable(ID_PARAM) final String id, @RequestBody final Account.Builder account)
		throws DomainException {
		account.setId(id);
		return accountService.update(account.build());
	}

	/**
	 * Delete an Account.
	 *
	 * @param id The id of the account to be deleted.
	 */
	@DeleteMapping(path = ACCOUNTS_ID)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(ID_PARAM) final String id) {
		//Boolean result =
		accountService.erase(id);
		// TODO handle result on false
		// 200 (OK) if the response includes an entity describing the status
		// * @return {@link Boolean#TRUE} if the account is removed after this operation. {@link Boolean#FALSE} else.
		// return result;
		// 204 (No Content) if the action has been enacted but the response does not include an entity
	}

	/**
	 * Create a new {@link Entry} with the given values.
	 *
	 * @param entry the entry to create.
	 * @return The entry as stored in the repository.
	 * @throws DomainException with {@link DomainFailure#DUPLICATE_ENTITY} if the given entry already exists.
	 * @throws DomainException with {@link DomainFailure#MISSING_ENTITY_REFERENCE} if the account for the entry does not exists.
	 */
	@PostMapping(path = ACCOUNTS_ENTRIES, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Entry create(@RequestBody final EntryBuilder entry) throws DomainException {
		// TODO handle failures while binding
		return accountService.create(entry.build());
	}
	/**
	 * Retrieve all entries for an accounts.
	 *
	 * @return all entries for the account
	@GetMapping(ACCOUNTS_ENTRIES)
	public List<Entry> retrieveAll(@PathVariable(ID_PARAM) final String id) {
		return accountService.retrieveAllEntries(id);
	}
	 */

}
