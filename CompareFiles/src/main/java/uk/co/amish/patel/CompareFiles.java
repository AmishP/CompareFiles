/**
 * 
 */
package uk.co.amish.patel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Class to compare all files in 2 directories to find files with the same
 * contents. Need to pass in the folder directories. 
 * 
 * E.g. java -jar CompareFiles-jar-with-dependencies.jar /folder/directory1/ /folder/directory2/
 * 
 * @author Amish Patel
 *
 */
public class CompareFiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args[0] == null || args[1] == null) {
			return;
		}
		File[] directory1 = getFilesList(args[0]);
		File[] directory2 = getFilesList(args[1]);
		startCompare(directory1, directory2);
	}

	/**
	 * 
	 * @param directory1
	 * @param directory2
	 */
	private static void startCompare(File[] directory1, File[] directory2) {
		List<String> sameFiles = new ArrayList<String>();
		for (File file1 : directory1) {
			for (File file2 : directory2) {
				if (file1.getName().equals(file2.getName())) {
					boolean contentSame = compareFileContents(file1, file2);
					if (contentSame) {
						sameFiles.add(file1.getName());
						System.out.println("Content is the same");
					}
				}
			}
		}

		System.out.println("List of files with the same content: ");
		for (String sameFile : sameFiles) {
			System.out.println(sameFile);
		}

	}

	/**
	 * 
	 * @param file1
	 * @param file2
	 * @return
	 */
	private static boolean compareFileContents(File file1, File file2) {
		System.out.println("Starting to compare file " + file1 + " and " + file2);
		boolean fileCompare = false;
		try {
			fileCompare = FileUtils.contentEquals(file1, file2);
			// System.out.println("fileCompare " + fileCompare);
			return fileCompare;
		} catch (IOException e) {
			System.out.println("Error comparing file content " + e);
		}

		return fileCompare;

	}

	/**
	 * 
	 * @param directory
	 * @return
	 */
	private static File[] getFilesList(String directory) {
		System.out.println("Getting all files from " + directory);
		File[] listOfFiles = null;
		try {
			File folder = new File(directory);
			listOfFiles = folder.listFiles();
			if (listOfFiles.length != 0) {
				return listOfFiles;
			}
			System.out.println("Directory is empty " + directory);
		} catch (Exception e) {
			System.out.println("Error getting files" + e);
		}
		return listOfFiles;
	}

}
