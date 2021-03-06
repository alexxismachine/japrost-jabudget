package de.japrost.jabudget.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test the {@link AggregateMember}.
 */
public class AggregateMemberTest {

	private AggregateMember cut = new AggregateMember() {

		private String key = "a key";

		@Override
		public String key() {
			return key;
		}
	};

	/**
	 * Same instances are equal.
	 */
	@Test
	 void isEqualOnSame() {
		// given
		// when
		boolean actual = cut.equals(cut);
		// then
		assertThat(actual).isTrue();
	}

	/**
	 * Same instances are equal.
	 */
	@Test
	 void isNotEqualOnOther() {
		// given
		// when
		boolean actual = cut.equals("");
		// then
		assertThat(actual).isFalse();
	}
}
