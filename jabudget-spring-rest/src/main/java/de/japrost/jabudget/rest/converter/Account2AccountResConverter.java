package de.japrost.jabudget.rest.converter;

import java.util.List;
import java.util.stream.Collectors;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.rest.model.AccountRes;

/**
 * Converter from {@link Account} to {@link AccountRes}.
 */
public class Account2AccountResConverter {

	/**
	 * Convert from source to target.
	 *
	 * @param source source
	 * @return target
	 */
	public AccountRes convert(final Account source) {
		var target = new AccountRes();
		target.setId(source.getId());
		target.setName(source.getName());
		return target;
	}

	/**
	 * Convert list from source to target.
	 *
	 * @param sources sources
	 * @return targets
	 */
	public List<AccountRes> convertList(final List<Account> sources) {
		return sources.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}
}
