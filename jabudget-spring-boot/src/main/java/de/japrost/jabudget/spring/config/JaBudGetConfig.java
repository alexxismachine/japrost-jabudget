package de.japrost.jabudget.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.japrost.jabudget.service.AccountService;

@Configuration
public class JaBudGetConfig {

	@Bean
	public AccountService accountService() {
		return new AccountService();
	}
}
