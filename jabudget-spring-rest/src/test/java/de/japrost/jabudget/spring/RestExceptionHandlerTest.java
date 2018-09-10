package de.japrost.jabudget.spring;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.DomainFailure;
import de.japrost.jabudget.domain.account.Account;

/**
 * Test the {@link RestExceptionHandler}.
 */
@RunWith(Parameterized.class)
public class RestExceptionHandlerTest {

	private RestExceptionHandler cut;
	private final DomainFailure input;
	private final HttpStatus expected;

	/**
	 * Constructor for paramters.
	 *
	 * @param input input value
	 * @param expected expected result
	 */
	public RestExceptionHandlerTest(final DomainFailure input, final HttpStatus expected) {
		this.input = input;
		this.expected = expected;
	}

	/**
	 * Set up each test.
	 */
	@Before
	public void setUp() {
		cut = new RestExceptionHandler();
	}

	/**
	 * @return the data for the parameters
	 */
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { DomainFailure.DUPLICATE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY },
				{ DomainFailure.MISSING_ENTITY, HttpStatus.NOT_FOUND },
				{ DomainFailure.ENTITY_NOT_AVAILABLE, HttpStatus.NO_CONTENT }, { null, HttpStatus.INTERNAL_SERVER_ERROR } });
	}

	/**
	 * test the mapping from {@link DomainFailure} to {@link HttpStatus}.
	 */
	@Test
	public void handleMapping() {
		DomainException domainException;
		if (Objects.nonNull(input)) {
			domainException = new DomainException(input);
		} else {
			domainException = null;
		}
		final ResponseEntity<Account> responseEntity = cut.handleDomainException(domainException);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(expected);
	}
}
