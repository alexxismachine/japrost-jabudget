package de.japrost.jabudget.domain.account;

/**
 * A member of an aggregate. Has a key as unique identifier.
 */
public abstract class AggregateMember {

	/** Separator between parts of a key */
	public static final String KEY_SEPARATOR = ":";

	/**
	 * Get the key of the {@link AggregateMember}.
	 *
	 * @return the key, composed with the KEY_SEPARATOR.
	 */
	public abstract String key();

	/**
	 * {@inheritDoc} <br>
	 * <strong>This implementation</strong> uses the {@link AggregateMember#key()}.
	 */
	@Override
	public int hashCode() {
		return key().hashCode();
	}

	/**
	 * {@inheritDoc} <br>
	 * <strong>This implementation</strong> uses the {@link AggregateMember#key()}.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AggregateMember)) {
			return false;
		}
		final AggregateMember other = (AggregateMember) obj;
		if (key().equals(other.key())) {
			return true;
		}
		return false;
	}

}
