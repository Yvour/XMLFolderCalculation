package reader;

import java.io.File;

public class FolderProcessor {

	public static void processFolder(String inputFolder, String outputFolder) {
		File dir = new File(inputFolder);
		File[] files = dir.listFiles((d, name) -> name.endsWith(".xml"));
		if (files != null)
			for (File file : files) {
				FileProcessor.processFile(file, outputFolder);
			}
	}

}
