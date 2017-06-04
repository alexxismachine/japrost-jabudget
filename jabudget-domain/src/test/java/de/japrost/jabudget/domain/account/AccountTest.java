package de.japrost.jabudget.domain.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.outsideMyBox.testUtils.BeanLikeTester;
import org.outsideMyBox.testUtils.BeanLikeTester.ConstructorSignatureAndPropertiesMapping;
import org.outsideMyBox.testUtils.BeanLikeTester.PropertiesAndValues;

public class AccountTest {

	@Test
	public void id_mustNotBeNull() {
		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(() -> new Account(null))
				.withMessage("'id' MUST NOT be null.");
	}

	@Test
	public void equals_onId() throws Exception {
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

	@Test
	public void equals_UnHappy() throws Exception {
		final Account one = new Account("one");
		one.setName("one name");
		assertThat(one).isNotEqualTo(null);
		assertThat(one).isNotEqualTo(this);
	}

	@Test
	public void hashCode_onId() throws Exception {
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

	@Test
	public void beanLike() {
		final ConstructorSignatureAndPropertiesMapping mapping = new ConstructorSignatureAndPropertiesMapping();
		final List<Class<?>> idConstructor = Arrays.asList(String.class);
		mapping.put(idConstructor, Arrays.asList("id"));
		final PropertiesAndValues defaults = new PropertiesAndValues();
		defaults.put("id", "an id");
		defaults.put("name", null);
		final PropertiesAndValues other = new PropertiesAndValues();
		other.put("id", "an other id");
		other.put("name", "a name");
		final BeanLikeTester blt = new BeanLikeTester(Account.class, mapping);

		blt.testDefaultValues(defaults);
		blt.testMutatorsAndAccessors(defaults, other);
		blt.testToString(defaults, other);
	}
}
