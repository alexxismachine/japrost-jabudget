package de.japrost.jabudget.service;

import de.japrost.jabudget.repository.AccountRepository;
import de.japrost.jabudget.serialization.DomainStore;
import de.japrost.jabudget.serialization.Serialization;

/**
 * Business service for import and export the whole domain.
 */
public class SerializationService {

	private final Serialization serialization;
	private final AccountRepository accountRepository;

	/**
	 * Initialize with all dependencies.
	 *
	 * @param serialization the store to im- and export.
	 * @param accountRepository the reository to read and write.
	 */
	public SerializationService(final Serialization serialization, final AccountRepository accountRepository) {
		this.serialization = serialization;
		this.accountRepository = accountRepository;
	}

	/**
	 * Serialize all data from the repositories.
	 */
	public void serialize() {
		final DomainStore domainStore = new DomainStore();
		domainStore.setAccounts(accountRepository.findAll());
		serialization.serialize(domainStore);
	}

	/**
	 * Deserialize all data into the repositories.
	 */
	public void deserialize() {
		final DomainStore domainStore = serialization.deserialize();
		accountRepository.replaceAll(domainStore.getAccounts());
	}
}
