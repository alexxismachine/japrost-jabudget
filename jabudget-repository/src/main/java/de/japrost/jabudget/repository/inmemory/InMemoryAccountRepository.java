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

	private final Map<String, Account> storage = new HashMap<>();

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> stores and gives defensive copies of the given account.
	 */
	@Override
	public Account create(final Account account) throws DomainException {
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
	public Account update(final Account account) throws DomainException {
		if (!storage.containsKey(account.getId())) {
			throw new DomainException(DomainFailure.MISSING_ENTITY);
		}
		return putInStoreDefensively(account);

	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> clears existing and adds defensive copies to store.
	 */
	@Override
	public void replaceAll(final Set<Account> accounts) {
		storage.clear();
		accounts.forEach(this::putInStoreDefensively);
	}

	private Account putInStoreDefensively(final Account account) {
		final Account accountToStore = new Account.Builder(account).build();
		storage.put(account.getId(), accountToStore);
		return new Account.Builder(account).build();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> gives a defensive copy of the stored accounts.
	 */
	@Override
	public Optional<Account> findById(final String id) {
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
	public Boolean delete(final String accountId) {
		storage.remove(accountId);
		return Boolean.TRUE;
	}

}
