package nonactive;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.ls.LSInput;

public class SequentialSimplify {
	final static char END = '[';
	public static void main(String[] args) throws IOException{
		String path = "MillionSongSubset/data";
		System.out.println("Begin");
		extract(path, 0);
	}
	private static void extract(String path, int depth) throws IOException {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        File dest = new File("Sequential");
		        FileUtils.copyFileToDirectory(listOfFiles[i], dest);
		        System.out.println("Copied file: " + listOfFiles[i].getName() + System.lineSeparator() + "From dir: " + path + System.lineSeparator());
		      } else if (listOfFiles[i].isDirectory()) {
		        extract(path + "/" + listOfFiles[i].getName(),depth+1);
		      }
		    }
	}
}
