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

	/**
	 * Builder for {@link Account}s.
	 */
	public static class Builder {

		/** The identity of an Account. MUST NOT be {@code null}. */
		private String id;
		/** The given name of an Account. */
		private String name;

		/**
		 * Create an empty account builder.
		 */
		public Builder() {
		}

		/**
		 * Create an account builder with all required fields.
		 *
		 * @param id the id. MUST NOT be {@code null}.
		 */
		public Builder(String id) {
			this.id = id;
		}

		/**
		 * Create an account builder by example;
		 * 
		 * @param account the example account.
		 */
		public Builder(Account account) {
			this.id = account.id;
			this.name = account.name;
		}

		/**
		 * Create an empty account builder.
		 * 
		 * @return the {@link Builder}.
		 */
		public static Builder builder() {
			return new Builder();
		}

		/**
		 * Create an account builder with all required fields.
		 *
		 * @param id the id. MUST NOT be {@code null}.
		 * @return the {@link Builder}.
		 */
		public static Builder builder(String id) {
			return new Builder(id);
		}

		/**
		 * Create an account builder by example;
		 * 
		 * @param account the example account.
		 * @return the {@link Builder}.
		 */
		public static Builder builder(Account account) {
			return new Builder(account);
		}

		/**
		 * Build the account.
		 * 
		 * @return a new account instance.
		 */
		public Account build() {
			Account result = new Account(id);
			result.name = name;
			return result;
		}

		/**
		 * Sets the id of an Account. MUST NOT be {@code null}.
		 *
		 * @param id the id of an Account
		 * @return this
		 */
		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the given name of an Account.
		 *
		 * @param name the new given name of an Account
		 * @return this
		 */
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
	}
}
