package de.japrost.jabudget.serialization;

/**
 * Domain serializator and deserializator.
 */
public interface Serialization {

	/**
	 * Serialize the domain.
	 *
	 * @param domainStore the doamin to serialize.
	 */
	void serialize(DomainStore domainStore);

	/**
	 * Deserialize the domaion.
	 *
	 * @return the deserialized domain.
	 */
	DomainStore deserialize();

}
