package de.japrost.jabudget.spring;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;

/**
 * Exception handlers for exceptions raised in a controller.
 */
@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * Handle {@link DomainException}s e.g. from repositories.
	 *
	 * @param e the exception to handle
	 * @return an empty {@link ResponseEntity} with the according {@link HttpStatus}.
	 */
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Account> handleDomainException(final DomainException e) {
		if (Objects.isNull(e)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		final DomainFailure domainFailure = e.getFailure();
		switch (domainFailure) {
			case DUPLICATE_ENTITY:
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			case MISSING_ENTITY:
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			case ENTITY_NOT_AVAILABLE:
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			default:
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
