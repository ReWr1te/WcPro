import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.awt.Frame;
import java.awt.FileDialog;

public class CommandParser {
	// handle the input parameters
	public String paraHandling(String[] args) {
		String path = null;
		if (args.length == 0) {
			Frame frame = new Frame();
			FileDialog openFile = new FileDialog(frame, "打开文件", FileDialog.LOAD);
			openFile.setVisible(true);
			String dirName = openFile.getDirectory();
			String fileName = openFile.getFile();
			if ((dirName == null) || (fileName == null)) {
				System.out.println("You didn't choose a file!");
				frame.dispose();
				return null;
			} else {
				path = dirName + fileName;
				frame.dispose();
				return path;
			}
		} else if (args.length > 1){
			System.out.println("Too many parameters! Please input just one parameter!");
			return null;
		} else {
			if (args[0].indexOf("*.") != -1) {
				System.out.println("Too many files! Please input just one file!");
				return null;
			}
			if (args[0].indexOf(".txt") == -1) {
				System.out.println("Wrong file type! Please input .txt files!");
				return null;
			}
			if (args[0].indexOf(":") != -1 || args[0].indexOf("/") != -1 || args[0].indexOf("\\") != -1) {
				path = args[0];
			} else {
				path = getPath(args[0]);
			}
			return path;
		}
	}

	// get the content
	public String getContent(String path) {

		// initiate variables
		String content = "", line;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer sb= null;

		// begin reading
		try {

			// read file according to address
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			sb = new StringBuffer();

			// read all lines
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n'); // add \n to go to next line
			}
			content = sb.toString(); // change file contents to the type of String

		} catch (FileNotFoundException e) { // to catch exceptions
			//e.printStackTrace();
			System.out.println("This File doesn't exist!");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) { // to check whether the buffered reader is closed
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	
	// get the address
	public String getPath(String fileName) {
		// initiate variables
		String os, path = "";
		// differ operating systems
		os = System.getProperty("os.name");
		if (os.indexOf("Mac") != -1)
			path = System.getProperty("user.dir") + "/" + fileName; // OSX
		else if (os.indexOf("Win") != -1)
			path = System.getProperty("user.dir") + "\\" + fileName; // Windows
		return path;
	}
	
	// check half-width characters
	public int halfWidthChar(String content) {
		if (content.getBytes().length == content.length())
			return 1;
		else {
			System.out.println("half-width characters only!");
			return 0;
		}
	}

	// get the content in a file and return the result
    public String parseCommand(String[] args) {

    	// initiate variables
		// initiate content file, each line, filename and address to store related info
		String path, content;

		// call the functions to get address and then content
		path = paraHandling(args);
		if (path == null)
			return null; // to return a void result
		else {
			content = getContent(path);
			//judge whether content is null
			if (content==null||halfWidthChar(content) == 0) {
				return null;
			} else {
				return content;
			}
		}
    }
}