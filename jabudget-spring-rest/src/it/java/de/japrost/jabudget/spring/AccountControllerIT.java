package de.japrost.jabudget.spring;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.domain.fixtures.account.AccountFixtures;
import de.japrost.jabudget.rest.converter.Account2AccountResConverter;
import de.japrost.jabudget.rest.converter.AccountRes2AccountConverter;
import de.japrost.jabudget.service.AccountService;

/**
 * Test the {@link AccountController}.
 */
public class AccountControllerIT {

	private MockMvc mockMvc;
	private AccountService accountService;
	private final AccountFixtures accountFixtures = new AccountFixtures();

	/**
	 * Set up each test.
	 */
	@BeforeEach
	public void setup() {
		Account2AccountResConverter accountRes2AccountConverter = new Account2AccountResConverter();
		accountService = Mockito.mock(AccountService.class);
		final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
		exceptionHandlerExceptionResolver.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(
						new AccountController(accountService, new AccountRes2AccountConverter(), accountRes2AccountConverter))
				.setHandlerExceptionResolvers(exceptionHandlerExceptionResolver).build();
	}

	/**
	 * Simple delegation to sevice.
	 *
	 * @throws Exception never
	 */
	@Test
	void accounts_delegates() throws Exception {
		// given
		// when
		mockMvc.perform(MockMvcRequestBuilders.get(PathMapping.BASE + PathMapping.ACCOUNTS));
		// then
		Mockito.verify(accountService).retrieveAll();
	}

	/**
	 * Simple delegation to service.
	 *
	 * @throws Exception never
	 */
	@Test
	void accounts_id_delegates() throws Exception {
		// given
		final Account account = accountFixtures.createDefault();
		Mockito.when(accountService.retrieveById(account.getId())).thenReturn(Optional.of(account));
		// when
		mockMvc.perform(MockMvcRequestBuilders.get(PathMapping.BASE + PathMapping.ACCOUNTS + "/" + account.getId())
				.accept(MediaType.APPLICATION_JSON));
		// then
	}

	// TODO test remaining methods
	// TODO test with umlauts to validate encoding
}
