package de.japrost.jabudget.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.service.AccountService;

@RestController
@RequestMapping("/rest")
public class AccountController {

	private final AccountService accountService;

	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/account/{id}")
	public Account retrieveById(@PathVariable("id") final String id) {
		return accountService.retrieveById(id);
	}
}
