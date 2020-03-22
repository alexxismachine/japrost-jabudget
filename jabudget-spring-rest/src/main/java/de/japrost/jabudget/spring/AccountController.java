package de.japrost.jabudget.spring;

import static de.japrost.jabudget.spring.PathMapping.ACCOUNTS;
import static de.japrost.jabudget.spring.PathMapping.ACCOUNTS_ENTRIES;
import static de.japrost.jabudget.spring.PathMapping.ACCOUNTS_ID;
import static de.japrost.jabudget.spring.PathMapping.BASE;
import static de.japrost.jabudget.spring.PathMapping.ID_PARAM;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.account.Entry;
import de.japrost.jabudget.domain.account.EntryBuilder;
import de.japrost.jabudget.rest.converter.Account2AccountResConverter;
import de.japrost.jabudget.rest.converter.AccountRes2AccountConverter;
import de.japrost.jabudget.rest.model.AccountRes;
import de.japrost.jabudget.service.AccountService;

/**
 * REST controller for {@link AccountRes}s.
 */
@RestController
@RequestMapping(BASE)
public class AccountController {

	private final AccountService accountService;
	private final AccountRes2AccountConverter accountRes2AccountConverter;
	private final Account2AccountResConverter account2AccountResConverter;

	/**
	 * Instantiate with necessary dependencies.
	 *
	 * @param accountService the {@link AccountService} to use.
	 * @param accountRes2AccountConverter the {@link Account2AccountResConverter} to use.
	 * @param account2AccountResConverter the {@link AccountRes2AccountConverter} to use.
	 */
	public AccountController(final AccountService accountService,
			final AccountRes2AccountConverter accountRes2AccountConverter,
			final Account2AccountResConverter account2AccountResConverter) {
		this.accountService = accountService;
		this.accountRes2AccountConverter = accountRes2AccountConverter;
		this.account2AccountResConverter = account2AccountResConverter;
	}

	/**
	 * Retrieve all accounts.
	 *
	 * @return all accounts
	 */
	@GetMapping(ACCOUNTS)
	public List<AccountRes> retrieveAll() {
		return account2AccountResConverter.convertList(accountService.retrieveAll());
	}

	/**
	 * Retrieve a single {@link AccountRes} by id.
	 *
	 * @param id the id to look for
	 * @return the found {@link AccountRes}
	 * @throws DomainException if finding fails
	 */
	@GetMapping(ACCOUNTS_ID)
	public AccountRes retrieveById(@PathVariable(ID_PARAM) final String id) throws DomainException {
		final Optional<Account> account = accountService.retrieveById(id);
		if (account.isPresent()) {
			return account2AccountResConverter.convert(account.get());
		} else {
			// TODO do not use DomainException in Controller.
			throw new DomainException(DomainFailure.ENTITY_NOT_AVAILABLE);
		}
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
	 * @param id the id to look for
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
	 * @throws DomainException with {@link DomainFailure#MISSING_ENTITY_REFERENCE} if the account for the entry does not
	 *         exists.
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
	 * @GetMapping(ACCOUNTS_ENTRIES) public List<Entry> retrieveAll(@PathVariable(ID_PARAM) final String id) { return
	 *                               accountService.retrieveAllEntries(id); }
	 */

}
