package de.japrost.jabudget.rest.model;

/**
 * An account.
 */
public class AccountRes {

	/** The unique id */
	private String id;
	/** The given name */
	private String name;

	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the unique id.
	 *
	 * @param id the new unique id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Gets the given name.
	 *
	 * @return the given name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the given name.
	 *
	 * @param name the new given name
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
