package de.japrost.jabudget.domain.account;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * An account in JaBudGet.<br>
 * The Account is identified by its id.
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String id;
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
	 * Gets the identity of the Account.
	 *
	 * @return the identity of an Account.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the given name of the Account.
	 *
	 * @return the given name. If no name is given returns an empty String.
	 */
	public String getName() {
		if (Objects.nonNull(name)) {
			return name;
		} else {
			return "";
		}
	}

	/**
	 * Sets the given name of the Account.
	 *
	 * @param name the new given name. SHOULD NOT BE {@code null}.
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

		private String id;
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
		public Builder(final String id) {
			this.id = id;
		}

		/**
		 * Create an account builder by example;
		 *
		 * @param account the example account. MAY BE {@code null}.
		 */
		public Builder(final Account account) {
			if (account != null) {
				this.id = account.id;
				this.name = account.name;
			}
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
		public static Builder builder(final String id) {
			return new Builder(id);
		}

		/**
		 * Create an account builder by example;
		 *
		 * @param account the example account.
		 * @return the {@link Builder}.
		 */
		public static Builder builder(final Account account) {
			return new Builder(account);
		}

		/**
		 * Build the account.
		 *
		 * @return a new account instance.
		 */
		public Account build() {
			final Account result = new Account(id);
			result.name = name;
			return result;
		}

		/**
		 * Build the account if it would be valid.
		 *
		 * @return a new account instance or an empty optional.
		 */
		public Optional<Account> buildOptional() {
			if (id == null) {
				return Optional.empty();
			}
			final Account result = new Account(id);
			result.name = name;
			return Optional.of(result);
		}

		/**
		 * Sets the id of an Account. MUST NOT be {@code null}.
		 *
		 * @param id the id of an Account
		 * @return this
		 */
		public Builder setId(final String id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the given name of an Account.
		 *
		 * @param name the new given name of an Account
		 * @return this
		 */
		public Builder setName(final String name) {
			this.name = name;
			return this;
		}
	}
}
