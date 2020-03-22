package de.japrost.jabudget.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.japrost.jabudget.rest.converter.Account2AccountResConverter;
import de.japrost.jabudget.rest.converter.AccountRes2AccountConverter;
import de.japrost.jabudget.service.AccountService;
import de.japrost.jabudget.service.SerializationService;
import de.japrost.jabudget.spring.AccountController;
import de.japrost.jabudget.spring.SerializationController;

@Configuration
public class SpringRestConfiguration {

	@Bean
	AccountController accountController(final AccountService accountService,
			final Account2AccountResConverter account2AccountConverter,
			final AccountRes2AccountConverter accountRes2AccountConverter) {
		return new AccountController(accountService, accountRes2AccountConverter, account2AccountConverter);
	}

	@Bean
	SerializationController serializationController(final SerializationService serializationService) {
		return new SerializationController(serializationService);
	}

	@Bean
	Account2AccountResConverter account2AccountConverter() {
		return new Account2AccountResConverter();
	}

	AccountRes2AccountConverter accountRes2AccountConverter() {
		return new AccountRes2AccountConverter();
	}
}
