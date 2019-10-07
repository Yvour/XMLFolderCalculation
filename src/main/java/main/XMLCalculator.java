package main;

import reader.FolderProcessor;

public class XMLCalculator {
	public static void main(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Two arguments are not provided");
		}
		String inputFolder = args[0];
		String outputFolder = args[1];
		FolderProcessor.processFolder(inputFolder, outputFolder);

	}
}
