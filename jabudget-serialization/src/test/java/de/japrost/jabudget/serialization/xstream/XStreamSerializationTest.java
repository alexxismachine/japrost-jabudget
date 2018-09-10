package de.japrost.jabudget.serialization.xstream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import de.japrost.jabudget.serialization.DomainStore;
import de.japrost.jabudget.serialization.DomainStoreFixture;

/**
 * Test the {@link XStreamSerialization}.
 */
public class XStreamSerializationTest {

	private static final String DEFAULT_DOMAINSTORE_FILE_NAME = "XStreamDefaultDomainStore.xml";
	private static final String MULTI_DOMAINSTORE_FILE_NAME = "XStreamMultiDomainStore.xml";
	private XStreamSerialization cut;
	private final File resourcesDirectory = new File("src/test/resources");
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private InputStream defaultDomainStore;
	private InputStream multiDomainStore;

	/**
	 * Set up each test.
	 *
	 * @throws Exception on failure
	 */
	@Before
	public void setUp() throws Exception {
		defaultDomainStore = new FileInputStream(new File(resourcesDirectory, DEFAULT_DOMAINSTORE_FILE_NAME));
		multiDomainStore = new FileInputStream(new File(resourcesDirectory, MULTI_DOMAINSTORE_FILE_NAME));
	}

	/**
	 * Test main serialization features.
	 */
	@Test
	public void serialize_default() {
		// given
		cut = new XStreamSerialization(defaultDomainStore, out);
		final DomainStore domainStore = DomainStoreFixture.defaultDomainStore();
		// when
		cut.serialize(domainStore);
		// then
		final File expected = new File(resourcesDirectory, DEFAULT_DOMAINSTORE_FILE_NAME);
		Assertions.assertThat(expected).hasContent(out.toString());
	}

	/**
	 * Test advanced serialization features.
	 */
	@Test
	public void serialize_multi() {
		// given
		cut = new XStreamSerialization(multiDomainStore, out);
		final DomainStore domainStore = DomainStoreFixture.multiDomainStore();
		// when
		cut.serialize(domainStore);
		// then
		final File expected = new File(resourcesDirectory, MULTI_DOMAINSTORE_FILE_NAME);
		Assertions.assertThat(expected).hasContent(out.toString());
	}

	/**
	 * Test main deserialization features.
	 */
	@Test
	public void deserialize_default() {
		// given
		cut = new XStreamSerialization(defaultDomainStore, out);
		// when
		final DomainStore actual = cut.deserialize();
		// then
		final DomainStore expected = DomainStoreFixture.defaultDomainStore();
		Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
	}

	/**
	 * Test advanced deserialization features.
	 */
	@Test
	public void deserialize_multi() {
		// given
		cut = new XStreamSerialization(multiDomainStore, out);
		// when
		final DomainStore actual = cut.deserialize();
		// then
		final DomainStore expected = DomainStoreFixture.multiDomainStore();
		Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
	}
}
