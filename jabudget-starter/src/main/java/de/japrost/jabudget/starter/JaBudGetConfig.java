package de.japrost.jabudget.starter;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.japrost.jabudget.repository.AccountRepository;
import de.japrost.jabudget.repository.inmemory.InMemoryAccountRepository;
import de.japrost.jabudget.serialization.LazyFileInputStream;
import de.japrost.jabudget.serialization.LazyFileOutputStream;
import de.japrost.jabudget.serialization.Serialization;
import de.japrost.jabudget.serialization.xstream.XStreamSerialization;
import de.japrost.jabudget.service.AccountService;
import de.japrost.jabudget.service.SerializationService;

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
	 *
	 * @return an in memory repository.
	 */
	@Bean
	public AccountRepository accountRepository() {
		return new InMemoryAccountRepository();
	}

	@Bean
	SerializationService serializationService(final Serialization serialization,
			final AccountRepository accountRepository) {
		return new SerializationService(serialization, accountRepository);
	}

	@Bean
	Serialization serialization() throws IOException {
		final File parent = new File("src/main/resources");
		parent.mkdirs();
		final File file = new File(parent, "JaBudGet-DomainStore.xml");
		file.createNewFile();
		return new XStreamSerialization(new LazyFileInputStream(file), new LazyFileOutputStream(file));
	}
}