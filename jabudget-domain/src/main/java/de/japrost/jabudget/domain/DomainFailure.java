package de.japrost.jabudget.domain;

/**
 * Failure codes for {@link DomainException}.
 */
public enum DomainFailure {
	/**
	 * The entity to operate on already exists. E.g. when creating a new one.
	 */
	DUPLICATE_ENTITY,
	/**
	 * The entity to operate on does not exist. E.g. when try updating an existing one.
	 */
	MISSING_ENTITY,
	/**
	 * The entity to locate does not exist. E.g. when try finding an existing one.
	 */
	ENTITY_NOT_AVAILABLE;
}
