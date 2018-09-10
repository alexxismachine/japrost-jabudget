package de.japrost.jabudget.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.japrost.jabudget.service.AccountService;
import de.japrost.jabudget.service.SerializationService;
import de.japrost.jabudget.spring.AccountController;
import de.japrost.jabudget.spring.SerializationController;

@Configuration
public class SpringRestConfiguration {

	@Bean
	AccountController accountController(final AccountService accountService) {
		return new AccountController(accountService);
	}

	@Bean
	SerializationController serializationController(final SerializationService serializationService) {
		return new SerializationController(serializationService);
	}
}
