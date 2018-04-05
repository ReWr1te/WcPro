import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CommandParser {

    public String parseCommand(String[] args) {

    	// initiate variables

		// initiate content file, each line, filename and address to store related info
		String content = "", line = "", addr = "", filename = args[0], os = "";

		// differ operating systems
		os = System.getProperty("os.name");
		if (os.indexOf("Mac") != -1)
			addr = System.getProperty("user.dir") + "/" + filename; // OSX
		else if (os.indexOf("Win") != -1)
			addr = System.getProperty("user.dir") + "\\" + filename; // Windows

		// initiate file reader, buffered reader and string buffer to store file medially
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();

		// begin reading
		try {

			// read file according to address
			fileReader = new FileReader(addr);
			bufferedReader = new BufferedReader(fileReader);

			// read all lines
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append('\n'); // add \n to go to next line
			}
			
			content = stringBuffer.toString(); // change file contents to the type of String

		} catch (FileNotFoundException e) { // to catch exceptions
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (bufferedReader != null) { // to check whether the buffered reader is closed
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		return content; // to return the result
    }
}
