package de.japrost.jabudget.domain.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.outsideMyBox.testUtils.BeanLikeTester;
import org.outsideMyBox.testUtils.BeanLikeTester.ConstructorSignatureAndPropertiesMapping;
import org.outsideMyBox.testUtils.BeanLikeTester.PropertiesAndValues;

/**
 * Test the {@link Account}.
 */
public class AccountTest {

	/**
	 * The id of an {@link Account} MUST NOT be {@code null}.
	 */
	@Test
	 void id_mustNotBeNull() {
		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(() -> new Account(null))
				.withMessage("'id' MUST NOT be null.");
	}

	/**
	 * Two {@link Account}s are equal by their id.
	 */
	@Test
	 void equals_onId() {
		final Account one = new Account("one");
		one.setName("one name");
		final Account two = new Account("two");
		two.setName("two name");
		final Account another_one = new Account("one");
		another_one.setName("another one name");
		assertThat(one).isEqualTo(one);
		assertThat(one).isEqualTo(another_one);
		assertThat(one).isNotEqualTo(two);
		assertThat(two).isNotEqualTo(another_one);
	}

	/**
	 * {@link Account}s are not equal to {@code null} or an instance of an other class.
	 */
	@Test
	 void equals_UnHappy() {
		final Account one = new Account("one");
		one.setName("one name");
		assertThat(one).isNotEqualTo(null);
		assertThat(one).isNotEqualTo(this);
	}

	/**
	 * An {@link Account}s hash code is calculated only on the id.
	 */
	@Test
	 void hashCode_onId() {
		final Account one = new Account("one");
		one.setName("one name");
		final Account two = new Account("two");
		two.setName("two name");
		final Account another_one = new Account("one");
		another_one.setName("another one name");
		assertThat(one.hashCode()).isEqualTo(another_one.hashCode());
		assertThat(one.hashCode()).isNotEqualTo(two.hashCode());
		assertThat(two.hashCode()).isNotEqualTo(another_one.hashCode());

	}

	/**
	 * Test default values, getters and setters and toString.
	 */
	@Test
	 void beanLike() {
		// given
		final ConstructorSignatureAndPropertiesMapping mapping = new ConstructorSignatureAndPropertiesMapping();
		final List<Class<?>> idConstructor = Arrays.asList(String.class);
		mapping.put(idConstructor, Arrays.asList("id"));
		final PropertiesAndValues defaults = new PropertiesAndValues();
		defaults.put("id", "an id");
		defaults.put("name", "");
		final PropertiesAndValues other = new PropertiesAndValues();
		other.put("id", "an other id");
		other.put("name", "a name");
		final BeanLikeTester blt = new BeanLikeTester(Account.class, mapping);
		// when / then
		blt.testDefaultValues(defaults);
		blt.testMutatorsAndAccessors(defaults, other);
		blt.testToString(defaults, other);
	}

	/**
	 * Builder does not allow {@code null} as id.
	 */
	@Test
	 void builder_id_mustNotBeNull() {
		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(() -> new Account.Builder().build())
				.withMessage("'id' MUST NOT be null.");
		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(() -> new Account.Builder((String) null).build())
				.withMessage("'id' MUST NOT be null.");
		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(() -> new Account.Builder("an id").setId(null).build())
				.withMessage("'id' MUST NOT be null.");
		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(() -> new Account.Builder((Account) null).build())
				.withMessage("'id' MUST NOT be null.");
	}

	/**
	 * Empty Builder sets all values.
	 */
	@Test
	 void builder_empty_setsValues() {
		// given
		Account.Builder builder = Account.Builder.builder().setId("an id").setName("a name");
		// when
		Account actual = builder.build();
		// then
		assertThat(actual.getId()).isEqualTo("an id");
		assertThat(actual.getName()).isEqualTo("a name");
	}

	/**
	 * Required field Builder sets all values.
	 */
	@Test
	 void builder_requiredFields_setsValues() {
		// given
		Account.Builder builder = Account.Builder.builder("an id").setName("a name");
		// when
		Account actual = builder.build();
		// then
		assertThat(actual.getId()).isEqualTo("an id");
		assertThat(actual.getName()).isEqualTo("a name");
	}

	/**
	 * Copy Builder sets all values.
	 */
	@Test
	 void builder_Copy_setsValues() {
		// given
		Account example = new Account("an id");
		example.setName("a name");
		Account.Builder builder = Account.Builder.builder(example);
		// when
		Account actual = builder.build();
		// then
		assertThat(actual.getId()).isEqualTo("an id");
		assertThat(actual.getName()).isEqualTo("a name");
	}

	/**
	 * Copy Builder may be null.
	 */
	@Test
	 void builder_Copy_mayBeNull() {
		// given
		Account.Builder builder = Account.Builder.builder((Account) null);
		// when
		builder.setId("an id").setName("a name");
		Account actual = builder.build();
		// then
		assertThat(actual.getId()).isEqualTo("an id");
		assertThat(actual.getName()).isEqualTo("a name");
	}

	/**
	 * Builder creates empty optional on not all required fields are set.
	 */
	@Test
	 void builder_optional_empty() {
		// given
		Account.Builder builder = Account.Builder.builder((Account) null);
		// when
		Optional<Account> actual = builder.buildOptional();
		// then
		assertThat(actual).isEmpty();
	}

	/**
	 * Builder creates filled optional on all required fields are set.
	 */
	@Test
	 void builder_optional_filled() {
		// given
		Account.Builder builder = Account.Builder.builder((Account) null);
		builder.setId("an id");
		// when
		Optional<Account> actual = builder.buildOptional();
		// then
		assertThat(actual).isNotEmpty();
	}
}
