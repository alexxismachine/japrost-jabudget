package de.japrost.jabudget.domain;

/**
 * Failure codes for {@link DomainException}.
 */
public enum DomainFailure {
	/**
	 * The entity already exists. E.g. when creating a new one.
	 */
	DUPLICATE_ENTITY;
}
