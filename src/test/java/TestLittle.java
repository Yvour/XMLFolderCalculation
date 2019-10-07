import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.XMLCalculator;

public class TestLittle {

	public static int getFileCount(String folderpath) {
		File directory = new File(folderpath);
		int numberOfFiles = 0;
		for (File element : directory.listFiles()) {
			if (element.isDirectory()) { // count the files for this subdir
				throw new Error("No Directory to exist there");
			} else { // one more file
				numberOfFiles++;

			}
		}

		return numberOfFiles;
	}

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@SuppressWarnings("deprecation")
	@Test
	public void test() {

		try {
			File createdFolder = folder.newFolder("subfolder");
			Assert.assertEquals(TestLittle.getFileCount(createdFolder.getAbsolutePath()), 0);
			String path = "src/test/resources";

			File file = new File(path);
			String absolutePath = file.getAbsolutePath();

			String testInputPath = absolutePath + "\\" + "testinput";

			String[] args = new String[2];
			args[0] = testInputPath;

			args[1] = createdFolder.getAbsolutePath();
			XMLCalculator.main(args);

			Assert.assertEquals(TestLittle.getFileCount(createdFolder.getAbsolutePath()), 3);

			final File expected = new File(absolutePath + "\\expected\\b.xml");
			final File output = new File(createdFolder.getAbsoluteFile() + "\\b_result.xml");

			Assert.assertEquals(FileUtils.readLines(expected, Charset.defaultCharset()),
					FileUtils.readLines(output, Charset.defaultCharset()));

			final File expectedBig = new File(absolutePath + "\\expected\\a.xml");
			final File outputBig = new File(createdFolder.getAbsoluteFile() + "\\a_result.xml");

			Assert.assertEquals(FileUtils.readLines(expectedBig, Charset.defaultCharset()),
					FileUtils.readLines(outputBig, Charset.defaultCharset()));

			final File expectedDeep = new File(absolutePath + "\\expected\\c.xml");
			final File outputDeep = new File(createdFolder.getAbsoluteFile() + "\\c_result.xml");

			Assert.assertEquals(FileUtils.readLines(expectedDeep, Charset.defaultCharset()),
					FileUtils.readLines(outputDeep, Charset.defaultCharset()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
