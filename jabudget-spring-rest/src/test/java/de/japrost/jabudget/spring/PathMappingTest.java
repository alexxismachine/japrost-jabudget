package de.japrost.jabudget.spring;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Test the {@link PathMapping}.
 */
public class PathMappingTest implements PathMapping {

	/**
	 * Test that no duplicates exists in path definition
	 *
	 * @throws Exception never
	 */
	@Test
	public void noDuplicateNames() throws Exception {
		final Field[] declaredFields = PathMapping.class.getDeclaredFields();
		final Set<String> path = new HashSet<>();
		for (final Field field : declaredFields) {
			if (field.getType().isAssignableFrom(String.class)) {
				Assertions.assertThat(path.add((String) field.get(null))).isTrue();
			}
		}
	}

	// TODO how / where to put integration tests?
}
