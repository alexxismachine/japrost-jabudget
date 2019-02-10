package de.japrost.jabudget.domain.account;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

/**
 * An entry in an {@link Account}.<br>
 */
public class Entry extends AggregateMember implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String accountId;
	private final String code;
	private final String subject;

	// TODO use jabudegt-utils with requireNonNull
	// TODO write tests that fields could not be null
	/**
	 * Create an Entry.
	 */
	public Entry(String accountId, String code, String subject) {
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
	 * @return the identity of the Entries account.
	 */

	public String getAccountId() {
		return accountId;
	}

	/**
	 * Gets the code of the Entry.
	 *
	 * @return the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets subject of the Entry.
	 *
	 * @return the subject.
	 */
	public String getSubject() {
		return subject;
	}

	@Override
	public String key() {
		return accountId + KEY_SEPARATOR + code;
	}
}
