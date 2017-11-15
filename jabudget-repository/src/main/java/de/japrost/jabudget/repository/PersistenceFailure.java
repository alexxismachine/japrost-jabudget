package de.japrost.jabudget.repository;

/**
 * Failure codes for {@link PersistenceException}.
 */
public enum PersistenceFailure {
	/**
	 * The entity already exists. E.g. when creating a new one.
	 */
	DUPLICATE_ENTITY;
}
