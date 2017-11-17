package de.japrost.jabudget.domain;

/**
 * Failure codes for {@link DomainException}.
 */
public enum DomainFailure {
	/**
	 * The entity already exists. E.g. when creating a new one.
	 */
	DUPLICATE_ENTITY,
	/**
	 * The entity does not exist. E.g. when updating an existion one.
	 */
	MISSING_ENTITY;
}
