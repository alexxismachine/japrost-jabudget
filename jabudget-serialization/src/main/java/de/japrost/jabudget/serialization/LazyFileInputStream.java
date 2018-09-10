package de.japrost.jabudget.serialization;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * An {@link InputStream} that uses a {@link FileInputStream} internally, which is created when the first acces is done.
 * On {@link InputStream#close()} the internal {@link FileInputStream} will be closed and unset that next reading all
 * will get a new stream.
 */
// TODO move to a io project
public class LazyFileInputStream extends InputStream {

	private final File file;
	private FileInputStream delegate;

	/**
	 * Create a {@link LazyFileInputStream} to be based on the given {@link File}.
	 *
	 * @param file the file to use in the delegate.
	 */
	public LazyFileInputStream(final File file) {
		// TODO require non null
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#read()}.
	 */
	@Override
	public int read() throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.read();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#read(byte[])}.
	 */
	@Override
	public int read(final byte[] b) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.read(b);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#read(byte[], int, int)}.
	 */
	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.read(b, off, len);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#readNBytes(byte[], int, int)}.
	 */
	@Override
	public int readNBytes(final byte[] b, final int off, final int len) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.readNBytes(b, off, len);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#readAllBytes()}.
	 */
	@Override
	public byte[] readAllBytes() throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.readAllBytes();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#skip(long)}.
	 */
	@Override
	public long skip(final long n) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.skip(n);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#available()}.
	 */
	@Override
	public int available() throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.available();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> calls {@link FileInputStream#close()} on the delegate and unassignes the
	 * delegate.
	 */
	@Override
	public void close() throws IOException {
		if (delegate != null) {
			delegate.close();
			delegate = null;
		}
	}

	/**
	 * Initializes the delegate if not already done and delegates to {@link FileInputStream#getChannel()}.
	 *
	 * @return {@link FileInputStream#getChannel()}
	 * @throws FileNotFoundException see {@link FileInputStream#FileInputStream(File)}.
	 */
	public FileChannel getChannel() throws FileNotFoundException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.getChannel();
	}

	/**
	 * Initializes the delegate if not already done and delegates to {@link FileInputStream#getFD()}.
	 *
	 * @return {@link FileInputStream#getFD()}
	 * @throws IOException see {@link FileInputStream#getFD()}
	 */
	public final FileDescriptor getFD() throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.getFD();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes the delegate if not already done and delegates to
	 * {@link FileInputStream#transferTo(OutputStream)}.
	 */
	@Override
	public long transferTo(final OutputStream out) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.transferTo(out);
	}

	/**
	 * Tell if the delegate is initialized.
	 *
	 * @return {@code true} if there is a delegate.
	 */
	public boolean isOpen() {
		return delegate != null;
	}

	private void initDelegate() throws FileNotFoundException {
		delegate = new FileInputStream(file);
	}

}
