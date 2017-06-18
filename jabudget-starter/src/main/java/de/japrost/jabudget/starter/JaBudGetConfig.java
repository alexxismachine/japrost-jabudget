package de.japrost.jabudget.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.japrost.jabudget.repository.AccountRepository;
import de.japrost.jabudget.repository.inmemory.InMemoryAccountRepository;
import de.japrost.jabudget.service.AccountService;

@Configuration
public class JaBudGetConfig {

	@Bean
	public AccountService accountService() {
		return new AccountService(accountRepository());
	}

	@Bean
	public AccountRepository accountRepository() {
		return new InMemoryAccountRepository();
	}
}