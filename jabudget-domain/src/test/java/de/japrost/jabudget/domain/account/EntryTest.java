package de.japrost.jabudget.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.outsideMyBox.testUtils.BeanLikeTester;
import org.outsideMyBox.testUtils.BeanLikeTester.ConstructorSignatureAndPropertiesMapping;
import org.outsideMyBox.testUtils.BeanLikeTester.PropertiesAndValues;

/**
 * Test the {@link Entry}.
 */
public class EntryTest {

	/**
	 * Test default values, getters and setters and toString.
	 */
	@Test
	public void beanLike() {
		// given
		final ConstructorSignatureAndPropertiesMapping mapping = new ConstructorSignatureAndPropertiesMapping();
		final List<Class<?>> idConstructor = Arrays.asList(String.class, String.class, String.class);
		mapping.put(idConstructor, Arrays.asList("accountId", "code", "subject"));
		final PropertiesAndValues defaults = new PropertiesAndValues();
		defaults.put("accountId", "an id");
		defaults.put("code", "a code");
		defaults.put("subject", "a subject");
		final PropertiesAndValues other = new PropertiesAndValues();
		other.put("accountId", "an other id");
		other.put("code", "an other code");
		other.put("subject", "an other subject");
		final BeanLikeTester blt = new BeanLikeTester(Entry.class, mapping);
		// when / then
		blt.testDefaultValues(defaults);
		blt.testMutatorsAndAccessors(defaults, other);
		blt.testToString(defaults, other);
	}

	/**
	 * Test that the key is composed right.
	 */
	@Test
	public void hasKey() {
		// given
		Entry cut = new Entry("an id", "a code", "a subject");
		// when
		String actual = cut.key();
		// then
		assertThat(actual).isEqualTo("an id:a code");
	}

	/**
	 * Test that the key is used as hash code.
	 */
	@Test
	public void hasHashCodeOfKey() {
		// given
		Entry cut = new Entry("an id", "a code", "a subject");
		// when
		int actual = cut.hashCode();
		// then
		assertThat(actual).isEqualTo("an id:a code".hashCode());
	}

	/**
	 * Test that the key is used in equals (happy).
	 */
	@Test
	public void hasEqualsOfKey() {
		// given
		Entry cut = new Entry("an id", "a code", "a subject");
		Entry cutEqual = new Entry("an id", "a code", "a subject");
		// when
		boolean actual = cut.equals(cutEqual);
		// then
		assertThat(actual).isTrue();
	}

	/**
	 * Test that the key is used in equals (un happy).
	 */
	@Test
	public void hasEqualsOfKey_unhappy_id() {
		// given
		Entry cut = new Entry("an id", "a code", "a subject");
		Entry cutEqual = new Entry("an other id", "a code", "an other subject");
		// when
		boolean actual = cut.equals(cutEqual);
		// then
		assertThat(actual).isFalse();
	}

	/**
	 * Test that the key is used in equals (un happy).
	 */
	@Test
	public void hasEqualsOfKey_unhappy_code() {
		// given
		Entry cut = new Entry("an id", "a code", "a subject");
		Entry cutEqual = new Entry("an id", "an other code", "an other subject");
		// when
		boolean actual = cut.equals(cutEqual);
		// then
		assertThat(actual).isFalse();
	}
}
