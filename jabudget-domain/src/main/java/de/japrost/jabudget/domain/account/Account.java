package de.japrost.jabudget.domain.account;

import static java.util.Objects.requireNonNull;

/**
 * An account in JaBudGet.<br>
 * The Account is identified by its id.
 */
public class Account {

	/** The identity of an Account. MUST NOT be {@code null}. */
	private final String id;
	/** The given name of an Account. */
	private String name;

	/**
	 * Create an account.
	 *
	 * @param id the id. MUST NOT be {@code null}.
	 */
	public Account(final String id) {
		requireNonNull(id, "'id' MUST NOT be null.");
		this.id = id;
	}

	/**
	 * Gets the identity of an Account. MUST NOT be {@code null}.
	 *
	 * @return the identity of an Account
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the given name of an Account.
	 *
	 * @return the given name of an Account
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the given name of an Account.
	 *
	 * @param name the new given name of an Account
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> uses just {@link Account#id}.
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> uses just {@link Account#id}.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Account other = (Account) obj;
		if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> shows all fields.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Account [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
