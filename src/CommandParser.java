import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CommandParser {

	// judge the input parameters
	public int paraHandling(String[] args) {
		if (args.length == 0) {
			System.out.println("No parameter! Please input one parameter!");
			return 0;
		} else if (args.length > 1){
			System.out.println("Too many parameters! Please input just one parameter!");
			return 0;
		} else {
			if (args[0].indexOf(":") != -1 || args[0].indexOf("/") != -1 || args[0].indexOf("\\") != -1) {
				System.out.println("No paths!");
				return 0;
			}
			if (args[0].indexOf("*.") != -1) {
				System.out.println("Too many files! Please input just one file!");
				return 0;
			}
			if (args[0].indexOf(".txt") == -1) {
				System.out.println("Wrong file type! Please input .txt files!");
				return 0;
			}
			return 1;
		}
	}

	// get the address
	public String getAddress(String fileName) {

		// initiate variables
		String os, address = "";

		// differ operating systems
		os = System.getProperty("os.name");
		if (os.indexOf("Mac") != -1)
			address = System.getProperty("user.dir") + "/" + fileName; // OSX
		else if (os.indexOf("Win") != -1)
			address = System.getProperty("user.dir") + "\\" + fileName; // Windows
		return address;
	}

	// get the content
	public String getContent(FileReader fr, BufferedReader br, StringBuffer sb, String address) {

		// initiate variables
		String content = "", line;

		// begin reading
		try {

			// read file according to address
			fr = new FileReader(address);
			br = new BufferedReader(fr);
			sb = new StringBuffer();

			// read all lines
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n'); // add \n to go to next line
			}
			content = sb.toString(); // change file contents to the type of String

		} catch (FileNotFoundException e) { // to catch exceptions
			e.printStackTrace();
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
		String address, content;

		// initiate file reader, buffered reader and string buffer to store file medially
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = null;

		// call the functions to get address and then content
		if (paraHandling(args) != 1)
			return null; // to return a void result
		else {
			address = getAddress(args[0]);
			content = getContent(fileReader, bufferedReader, stringBuffer, address);
			if (halfWidthChar(content) == 0) return null;
			else return content;
		}
    }
}