package de.japrost.jabudget.repository;

/**
 * Thrown on exceptions in repositories.
 */
// TODO generate messages 
public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;
	private final PersistenceFailure failure;

	/**
	 * Create an instance based on a {@link Throwable}.
	 * 
	 * @param failure the failure code
	 * @param cause the cause of the failure
	 */
	public PersistenceException(PersistenceFailure failure, Throwable cause) {
		super(cause);
		this.failure = failure;
	}

	/**
	 * Create an instance.
	 * 
	 * @param failure the failure code
	 */
	public PersistenceException(PersistenceFailure failure) {
		super();
		this.failure = failure;
	}

	/**
	 * Get the failure code.
	 * 
	 * @return the code
	 */
	public PersistenceFailure getFailure() {
		return failure;
	}

}
