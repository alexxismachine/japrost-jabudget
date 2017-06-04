package de.japrost.jabudget.service;

import de.japrost.jabudget.domain.account.Account;

public class AccountService {

	public Account retrieveById(final String id) {
		return new Account(id);
	}
}
