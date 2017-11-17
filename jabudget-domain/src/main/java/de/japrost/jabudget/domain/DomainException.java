package de.japrost.jabudget.domain;

/**
 * Thrown on exceptions handling the domain.
 */
// TODO generate messages 
public class DomainException extends Exception {

	private static final long serialVersionUID = 1L;
	private final DomainFailure failure;

	/**
	 * Create an instance based on a {@link Throwable}.
	 * 
	 * @param failure the failure code
	 * @param cause the cause of the failure
	 */
	public DomainException(DomainFailure failure, Throwable cause) {
		super(failure.toString(), cause);
		this.failure = failure;
	}

	/**
	 * Create an instance.
	 * 
	 * @param failure the failure code
	 */
	public DomainException(DomainFailure failure) {
		super(failure.toString());
		this.failure = failure;
	}

	/**
	 * Get the failure code.
	 * 
	 * @return the code
	 */
	public DomainFailure getFailure() {
		return failure;
	}

}
