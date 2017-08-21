package de.japrost.jabudget.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.japrost.jabudget.repository.AccountRepository;
import de.japrost.jabudget.repository.inmemory.InMemoryAccountRepository;
import de.japrost.jabudget.service.AccountService;

/**
 * Configuration for the services using an in memory repository.
 */
@Configuration
public class JaBudGetConfig {

	/**
	 * The account service.
	 * 
	 * @return the service using the accountRepository.
	 */
	@Bean
	public AccountService accountService() {
		return new AccountService(accountRepository());
	}

	/**
	 * The account repository.
	 * @return an in memory repository.
	 */
	@Bean
	public AccountRepository accountRepository() {
		return new InMemoryAccountRepository();
	}
}