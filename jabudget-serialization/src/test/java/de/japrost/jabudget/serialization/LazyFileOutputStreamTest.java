package de.japrost.jabudget.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link LazyFileOutputStream}.
 */
// TODO more tests when moving to io package.
public class LazyFileOutputStreamTest {

	private LazyFileOutputStream cut;
	private LazyFileInputStream helper;

	/**
	 * Set cut and helping reader with a file.
	 */
	@BeforeEach
	public void initCut() {
		final File file = new File("target/LazyFile.txt");
		cut = new LazyFileOutputStream(file);
		helper = new LazyFileInputStream(file);
	}

	/**
	 * Be kind and close before exit.
	 *
	 * @throws Exception never.
	 */
	@AfterEach
	public void closeCut() throws Exception {
		cut.close();
		helper.close();
	}

	/**
	 * Test that write() opens the delegate and delegates.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testWrite() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		cut.write(97);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(helper.read()).isEqualTo(97);
		assertThat(helper.read()).isEqualTo(-1);
	}

	/**
	 * Test that write() opens the delegate and delegates even two times.
	 *
	 * @throws Exception never.
	 */
	@Test
	 void testWrite_twice() throws Exception {
		// given
		assertThat(cut.isOpen()).isFalse();
		// when
		cut.write(97);
		cut.write(32);
		// then
		assertThat(cut.isOpen()).isTrue();
		assertThat(helper.read()).isEqualTo(97);
		assertThat(helper.read()).isEqualTo(32);
		assertThat(helper.read()).isEqualTo(-1);
	}

}
