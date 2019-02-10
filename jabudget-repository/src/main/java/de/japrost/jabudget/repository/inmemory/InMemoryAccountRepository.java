package de.japrost.jabudget.repository.inmemory;

import java.util.*;
import java.util.stream.Collectors;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.account.Entry;
import de.japrost.jabudget.domain.account.EntryBuilder;
import de.japrost.jabudget.repository.AccountRepository;

/**
 * {@link AccountRepository} storing in memory without persistence. Mostly for test and development.<br>
 * This implementation is not thread safe.
 */
public class InMemoryAccountRepository implements AccountRepository {

	private final Map<String, Account> storage = new HashMap<>();
	private final Map<String, Collection<Entry>> entryStorage = new HashMap<>();

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
		entryStorage.clear();
		accounts.forEach(this::putInStoreDefensively);
	}

	private Account putInStoreDefensively(final Account account) {
		final Account accountToStore = new Account.Builder(account).build();
		storage.put(account.getId(), accountToStore);
		if (!entryStorage.containsKey(account.getId())) {
			entryStorage.put(account.getId(), new ArrayList<Entry>());
		}
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
		entryStorage.remove(accountId);
		return Boolean.TRUE;
	}

	@Override
	public Entry create(final Entry entry) throws DomainException {
		Collection<Entry> entries = entryStorage.get(entry.getAccountId());
		if (Objects.isNull(entries)) {
			throw new DomainException(DomainFailure.MISSING_ENTITY_REFERENCE);
		}
		if (entries.contains(entry)) {
			throw new DomainException(DomainFailure.DUPLICATE_ENTITY);
		}
		return putInStoreDefensively(entries, entry);
	}

	private Entry putInStoreDefensively(final Collection<Entry> entries, final Entry value) {
		final Entry toStore = EntryBuilder.builder(value).build();
		entries.add(toStore);
		return EntryBuilder.builder(toStore).build();
	}

}
