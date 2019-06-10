package de.japrost.jabudget.domain.account;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

/**
 * An entry in an {@link Account}.<br>
 */
public class Entry extends AggregateMember implements Serializable {

	private static final long serialVersionUID = 1L;
	/** The identity of the Account the Entry resides in. */
	private final String accountId;
	/** The code of the entry. */
	private final String code;
	/** The subject of the entry */
	private final String subject;

	// TODO use jabudegt-utils with requireNonNull
	// TODO write tests that fields could not be null
	/**
	 * Create an Entry.
	 *
	 * @param accountId the account id
	 * @param code the code
	 * @param subject the subject
	 */
	public Entry(final String accountId, final String code, final String subject) {
		requireNonNull(accountId, "'accountId' MUST NOT be null.");
		requireNonNull(code, "'code' MUST NOT be null.");
		requireNonNull(subject, "'subject' MUST NOT be null.");
		this.accountId = accountId;
		this.code = code;
		this.subject = subject;
	}

	/**
	 * Gets the identity of the Account the Entry resides in.
	 *
	 * @return the identity of the Account the Entry resides in
	 */

	public String getAccountId() {
		return accountId;
	}

	/**
	 * Gets the code of the entry.
	 *
	 * @return the code of the entry
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the subject of the entry.
	 *
	 * @return the subject of the entry
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> concats accountId and code.
	 */

	@Override
	public String key() {
		return accountId + KEY_SEPARATOR + code;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> show all fields.
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entry [accountId=").append(accountId).append(", code=").append(code).append(", subject=")
				.append(subject).append("]");
		return builder.toString();
	}

}
