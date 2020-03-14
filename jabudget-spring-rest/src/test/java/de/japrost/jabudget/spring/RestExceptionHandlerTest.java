package de.japrost.jabudget.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;

/**
 * Test the {@link RestExceptionHandler}.
 */
public class RestExceptionHandlerTest {

	private RestExceptionHandler cut;

	/**
	 * Set up each test.
	 */
	@BeforeEach
	public void setUp() {
		cut = new RestExceptionHandler();
	}

	/**
	 * test the mapping from {@link DomainFailure} to {@link HttpStatus}.
	 *
	 * @param input The failure to throw.
	 * @param expected The expected result.
	 */
	@ParameterizedTest
	@MethodSource("handleMappingParameters")
	void handleMapping(final DomainFailure input, final HttpStatus expected) {
		DomainException domainException;
		if (Objects.nonNull(input)) {
			domainException = new DomainException(input);
		} else {
			domainException = null;
		}
		final ResponseEntity<Account> responseEntity = cut.handleDomainException(domainException);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(expected);
	}

	/**
	 * @return all arguments for the test.
	 */
	static Stream<Arguments> handleMappingParameters() {
		List<Arguments> args = new ArrayList<>();
		args.add(handleMappingArguments(DomainFailure.DUPLICATE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY));
		args.add(handleMappingArguments(DomainFailure.MISSING_ENTITY, HttpStatus.NOT_FOUND));
		args.add(handleMappingArguments(DomainFailure.ENTITY_NOT_AVAILABLE, HttpStatus.NO_CONTENT));
		args.add(handleMappingArguments(null, HttpStatus.INTERNAL_SERVER_ERROR));
		return args.stream();
	}

	/**
	 * Create an arguments for the test.
	 *
	 * @param input The failure to throw.
	 * @param expected The expected result.
	 * @return an arguments.
	 */
	static Arguments handleMappingArguments(final DomainFailure input, final HttpStatus expected) {
		return Arguments.arguments(input, expected);
	}
}
