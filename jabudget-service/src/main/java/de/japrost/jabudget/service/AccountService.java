package de.japrost.jabudget.service;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * Business service for {@link Account}s.
 */
public class AccountService {

	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account retrieveById(final String id) {
		accountRepository.create(new Account(id));
		return accountRepository.findById(id).get();
	}
}
