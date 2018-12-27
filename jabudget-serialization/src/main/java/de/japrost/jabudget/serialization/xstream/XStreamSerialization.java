package de.japrost.jabudget.serialization.xstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.serialization.DomainStore;
import de.japrost.jabudget.serialization.Serialization;

/**
 * {@link Serialization} using XStream.
 */
public final class XStreamSerialization implements Serialization {

	private final InputStream in;
	private final OutputStream out;
	private final XStream xstream;

	/**
	 * Initialize for read.
	 *
	 * @param in the stream to read from.
	 */
	public XStreamSerialization(final InputStream in) {
		this(in, null);
	}

	/**
	 * Initialize for write.
	 *
	 * @param out the stream to write to.
	 */
	public XStreamSerialization(final OutputStream out) {
		this(null, out);
	}

	/**
	 * Initialize for read and write.
	 *
	 * @param out the stream to write to.
	 * @param in the stream to read from.
	 */
	public XStreamSerialization(final InputStream in, final OutputStream out) {
		this.in = in;
		this.out = out;
		// TODO using dom driver for xpp has split package
		this.xstream = new XStream(new DomDriver());
		this.xstream.addImplicitCollection(DomainStore.class, "accounts");
		this.xstream.aliasPackage("jabudget.account", Account.class.getPackageName());
		this.xstream.aliasType("Store", DomainStore.class);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> writes to the {@link OutputStream} and closes it.
	 */
	@Override
	public void serialize(final DomainStore domainStore) {
		xstream.toXML(domainStore, out);
		try {
			out.close();
		} catch (final IOException e) {
			// TODO implement failure handling
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> reads from the {@link InputStream} and closes it.
	 */
	@Override
	public DomainStore deserialize() {
		final DomainStore result = (DomainStore) xstream.fromXML(in);
		try {
			in.close();
		} catch (final IOException e) {
			// TODO implement failure handling
			e.printStackTrace();
		}
		return result;
	}
}
