package de.japrost.jabudget.domain.account;

import java.util.Optional;

/**
 * Builder for {@link Entry}.
 */
public class EntryBuilder {

	private String accountId;
	private String code;
	private String subject;

	/**
	 * Create an empty builder.
	 */
	public EntryBuilder() {
	}

	/**
	 * Create a builder by example.
	 *
	 * @param example the example to use. MAY BE {@code null}.
	 */
	public EntryBuilder(final Entry example) {
		if (example != null) {
			this.accountId = example.getAccountId();
			this.code = example.getCode();
			this.subject = example.getSubject();
		}
	}

	/**
	 * Factory for a an empty builder.
	 *
	 * @return a new builder.
	 */
	public static EntryBuilder builder() {
		return new EntryBuilder();
	}

	/**
	 * Factory for a builder by example.
	 *
	 * @param example the example to use. MAY BE {@code null}.
	 * @return a builder filled with value of example.
	 */
	public static EntryBuilder builder(Entry example) {
		return new EntryBuilder(example);
	}
	/**
	 * Build the result.
	 *
	 * @return a new instance.
	 */
	public Entry build() {
		final Entry result = new Entry(accountId,code,subject);
		return result;
	}


	/**
	 * Build if the result would be valid.
	 *
	 * @return a instance or an empty optional.
	 */
	public Optional<Entry> buildOptional() {
		// TODO where to put this validation?
		if (accountId == null || code == null || subject == null) {
			return Optional.empty();
		}
		final Entry result = new Entry(accountId,code,subject);
		return Optional.of(result);
	}

	/**
	 * @see Entry#getAccountId()
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @see Entry#getCode()
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @see Entry#getSubject()
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
