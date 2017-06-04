package de.japrost.jabudget.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.service.AccountService;

@RestController
public class AccountController {

	private final AccountService accountService;

	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping("/")
	public Account retrieveById() {
		return accountService.retrieveById("test");
	}
}
