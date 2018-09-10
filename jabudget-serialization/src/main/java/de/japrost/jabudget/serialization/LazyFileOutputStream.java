package de.japrost.jabudget.serialization;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * An {@link OutputStream} that uses a {@link FileOutputStream} internally, which is created when first access is done.
 * On {@link OutputStream#close()} the internal {@link FileOutputStream} will be closed and unset that next writing will
 * get a new stream.
 */
//TODO move to a io project
public class LazyFileOutputStream extends OutputStream {

	private final File file;
	private FileOutputStream delegate;

	/**
	 * Create an instance based on the given File.
	 *
	 * @param file the file to use in the internal delegate.
	 */
	public LazyFileOutputStream(final File file) {
		// TODO require non null
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> calls {@link FileOutputStream#close()} on the delegate and unassigns the
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
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> calls {@link FileOutputStream#flush()} on the delegate if it is initalized.
	 */
	@Override
	public void flush() throws IOException {
		if (delegate != null) {
			delegate.flush();
		}
	}

	/**
	 * Initializes the delegate if not already done and delegates to {@link FileOutputStream#getChannel()}.
	 *
	 * @return {@link FileOutputStream#getChannel()}
	 * @throws FileNotFoundException if initialization of delegate fails. see
	 *         {@link FileOutputStream#FileOutputStream(File)}.
	 */
	public FileChannel getChannel() throws FileNotFoundException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.getChannel();
	}

	/**
	 * Initializes the delegate if not already done and delegates to {@link FileOutputStream#getFD()}.
	 *
	 * @return {@link FileOutputStream#getFD()}
	 * @throws IOException see {@link FileOutputStream#getFD()}.
	 * @throws FileNotFoundException if initialization of delegate fails. see
	 *         {@link FileOutputStream#FileOutputStream(File)}.
	 */
	public FileDescriptor getFD() throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		return delegate.getFD();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> innitializes the delegate if not already done and delegates to
	 * {@link FileOutputStream#write(byte[])}.
	 *
	 * @throws FileNotFoundException if initialization of delegate fails. see
	 *         {@link FileOutputStream#FileOutputStream(File)}.
	 */
	@Override
	public void write(final byte[] b) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		delegate.write(b);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> innitializes the delegate if not already done and delegates to
	 * {@link FileOutputStream#write(byte[], int, int)}.
	 *
	 * @throws FileNotFoundException if initialization of delegate fails. see
	 *         {@link FileOutputStream#FileOutputStream(File)}.
	 */
	@Override
	public void write(final byte[] b, final int off, final int len) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		delegate.write(b, off, len);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> innitializes the delegate if not already done and delegates to
	 * {@link FileOutputStream#write(int)}.
	 *
	 * @throws FileNotFoundException if initialization of delegate fails. see
	 *         {@link FileOutputStream#FileOutputStream(File)}.
	 */
	@Override
	public void write(final int b) throws IOException {
		if (delegate == null) {
			initDelegate();
		}
		delegate.write(b);
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
		delegate = new FileOutputStream(file);
	}

}
