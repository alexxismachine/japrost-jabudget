package de.japrost.jabudget.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.service.AccountService;

/**
 * REST controller for {@link Account}s.
 */
@RestController
@RequestMapping("/rest")
public class AccountController {

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
	@GetMapping("/accounts")
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
	@GetMapping("/accounts/{id}")
	public Account retrieveById(@PathVariable("id") final String id) throws DomainException {
		Optional<Account> account = accountService.retrieveById(id);
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
	@PostMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account create(@RequestBody Account.Builder account) throws DomainException {
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
	@PutMapping(path = "/accounts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account update(@PathVariable("id") final String id, @RequestBody Account.Builder account)
			throws DomainException {
		account.setId(id);
		return accountService.update(account.build());
	}

}
