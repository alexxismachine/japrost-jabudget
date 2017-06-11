package de.japrost.jabudget.service;

import java.util.ArrayList;
import java.util.List;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * Business service for {@link Account}s.
 */
public class AccountService {

	private final AccountRepository accountRepository;

	public AccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account create(final Account account) {
		return accountRepository.create(account);
	}

	public List<Account> retrieveAll() {
		return new ArrayList<>(accountRepository.findAll());
	}

	public Account retrieveById(final String id) {
		// TODO throw a business exception on missing account
		return accountRepository.findById(id).get();
	}
}
