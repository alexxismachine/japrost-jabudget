package de.japrost.jabudget.repository.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * {@link AccountRepository} storing in memory without persistence. Mostly for test and development.
 */
public class InMemoryAccountRepository implements AccountRepository {

	private Map<String, Account> storage = new HashMap<>();

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> stores and gives defensive copies of the given account.
	 */
	@Override
	public Account create(Account account) throws DomainException {
		if (storage.containsKey(account.getId())) {
			throw new DomainException(DomainFailure.DUPLICATE_ENTITY);
		}
		return putInStoreDefensively(account);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> stores and gives defensive copies of the given account.
	 */
	@Override
	public Account update(Account account) throws DomainException {
		if (!storage.containsKey(account.getId())) {
			throw new DomainException(DomainFailure.MISSING_ENTITY);
		}
		return putInStoreDefensively(account);

	}

	private Account putInStoreDefensively(Account account) {
		Account accountToStore = new Account.Builder(account).build();
		storage.put(account.getId(), accountToStore);
		return new Account.Builder(account).build();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> gives a defensive copy of the stored accounts.
	 */
	@Override
	public Optional<Account> findById(String id) {
		return Account.Builder.builder(storage.get(id)).buildOptional();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> gives defensive copies of all stored accounts.
	 */
	@Override
	public Set<Account> findAll() {
		return storage.values().stream().map(account -> new Account.Builder(account).build()).collect(Collectors.toSet());
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> returns {@link Boolean#TRUE} always.
	 */
	@Override
	public Boolean delete(String accountId) {
		storage.remove(accountId);
		return Boolean.TRUE;
	}

}
