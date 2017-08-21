package de.japrost.jabudget.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * Instanciate with necessary dependencies.
	 * 
	 * @param accountService the {@link AccountService} to use.
	 */
	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	// TODO implement retrieveAll
	
	/**
	 * Retrieve a single {@link Account} by id.
	 * 
	 * @param id the id to look for
	 * @return the found {@link Account}
	 */
	@GetMapping("/account/{id}")
	public Account retrieveById(@PathVariable("id") final String id) {
		return accountService.retrieveById(id);
	}
}
