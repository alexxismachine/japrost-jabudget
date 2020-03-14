package de.japrost.jabudget.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.nio.channels.FileChannel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link LazyFileInputStream}.
 */
public class LazyFileInputStreamTest {

	private LazyFileInputStream cut;

	/**
	 * Set cut with a file.
	 */
	@BeforeEach
	public void initCut() {
		final File file = new File("src/test/resources/LazyFile.txt");
		cut = new LazyFileInputStream(file);
	}

	/**
	 * Be kind and close before exit.
	 *
	 * @throws Exception never.
	 */
	@AfterEach
	public void closeCut() throws Exception {
		cut.close();
	}

	/**
	 * Test that read() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testRead() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final int actual = cut.read();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(97);
	}

	/**
	 * Test that read() does not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testRead_twice() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		cut.read();
		final int actual = cut.read();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(32);
	}

	/**
	 * Test that read(byte[] b) opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadByteArray() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		final byte[] bytes = new byte[5];
		// when
		final int actual = cut.read(bytes);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(5);
		assertThat(bytes).isEqualTo(new byte[] { 97, 32, 116, 101, 115 });
	}

	/**
	 * Test that read(byte[] b) not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadByteArray_twice() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		final byte[] bytes = new byte[5];
		// when
		cut.read(bytes);
		final int actual = cut.read(bytes);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(5);
		assertThat(bytes).isEqualTo(new byte[] { 116, 32, 102, 105, 108 });
	}

	/**
	 * Test that read(final byte[] b, final int off, final int len) opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadByteArrayOffset() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		final byte[] bytes = new byte[5];
		final int off = 1;
		final int len = 3;
		// when
		final int actual = cut.read(bytes, off, len);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(3);
		assertThat(bytes).isEqualTo(new byte[] { 0, 97, 32, 116, 0 });
	}

	/**
	 * Test that read(final byte[] b, final int off, final int len) not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadByteArrayOffset_twice() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		final byte[] bytes = new byte[5];
		final int off = 1;
		final int len = 3;
		// when
		cut.read();
		final int actual = cut.read(bytes, off, len);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(3);
		assertThat(bytes).isEqualTo(new byte[] { 0, 32, 116, 101, 0 });
	}

	/**
	 * Test that readNBytes(final byte[] b, final int off, final int len) opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadNBytes() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		final byte[] bytes = new byte[18];
		final int off = 2;
		final int len = 15;
		// when
		final int actual = cut.readNBytes(bytes, off, len);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(12);
		assertThat(bytes)
				.isEqualTo(new byte[] { 0, 0, 97, 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, 46, 0, 0, 0, 0 });
	}

	/**
	 * Test that readNBytes(final byte[] b, final int off, final int len) not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadNBytes_notOpening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		final byte[] bytes = new byte[18];
		final int off = 2;
		final int len = 15;
		// when
		cut.read();
		final int actual = cut.readNBytes(bytes, off, len);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(11);
		assertThat(bytes).isEqualTo(new byte[] { 0, 0, 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, 46, 0, 0, 0, 0, 0 });
	}

	/**
	 * Test that readAllBytes() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadAllBytes() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final byte[] actual = cut.readAllBytes();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(new byte[] { 97, 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, 46 });
	}

	/**
	 * Test that readAllBytes() not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testReadAllBytes_notOpening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		cut.read();
		final byte[] actual = cut.readAllBytes();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(new byte[] { 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, 46 });
	}

	/**
	 * Test that skip() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testSkip() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final long actual = cut.skip(2l);
		assertThat(cut.isOpen()).isTrue();
		final int read = cut.read();
		// then
		assertThat(actual).isEqualTo(2);
		assertThat(read).isEqualTo(116);
	}

	/**
	 * Test that skip() not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testSkip_notOpening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		cut.read();
		// when
		final long actual = cut.skip(2l);
		assertThat(cut.isOpen()).isTrue();
		final int read = cut.read();
		// then
		assertThat(actual).isEqualTo(2);
		assertThat(read).isEqualTo(101);
	}

	/**
	 * Test that available() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testAvailable() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final long actual = cut.available();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(12);
	}

	/**
	 * Test that available() not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testAvailable_notOpening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		cut.read();
		// when
		final long actual = cut.available();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(11);
	}

	/**
	 * Test that close() closes the delegate.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testClose() throws Exception {
		// given
		cut.read();
		assertThat(cut.isOpen()).isTrue();
		// when
		cut.close();
		// then
		assertThat(cut.isOpen()).isFalse();
	}

	/**
	 * Test that getChannel() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testGetChannel() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final FileChannel actual = cut.getChannel();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual.position()).isEqualTo(0);
	}

	/**
	 * Test that getChannel() not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testGetChannel_notOpening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		cut.read();
		// when
		final FileChannel actual = cut.getChannel();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual.position()).isEqualTo(1);
	}

	/**
	 * Test that getFD() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testGetFD() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final FileDescriptor actual = cut.getFD();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual.valid()).isTrue();
	}

	/**
	 * Test that getFD() not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testGetFD_notReopening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		cut.read();
		// when
		final FileDescriptor actual = cut.getFD();
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual.valid()).isTrue();
	}

	/**
	 * Test that transferTo() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testTransferTo() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final long actual = cut.transferTo(outputStream);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(12);
		assertThat(outputStream.toByteArray())
				.isEqualTo(new byte[] { 97, 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, 46 });
	}

	/**
	 * Test that transferTo() not reopen the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testTransferTo_notReopening() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		cut.read();
		// when
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final long actual = cut.transferTo(outputStream);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(actual).isEqualTo(11);
		assertThat(outputStream.toByteArray()).isEqualTo(new byte[] { 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, 46 });
	}

	/**
	 * Test that close() closes the delegate but next action reopens a new one.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testMulitClose() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		final byte[] first = cut.readAllBytes();
		final byte[] firstEmpty = cut.readAllBytes();
		assertThat(cut.isOpen()).isTrue();
		cut.close();
		assertThat(cut.isOpen()).isFalse();
		final byte[] second = cut.readAllBytes();
		assertThat(cut.isOpen()).isTrue();
		cut.close();
		assertThat(cut.isOpen()).isFalse();
		assertThat(first).isEqualTo(second);
		assertThat(firstEmpty).isEmpty();
	}

}
