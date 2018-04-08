import java.util.HashMap;

public class WcPro {
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser();
        ContentParser contentParser = new ContentParser();
        ResultWriter resultWriter = new ResultWriter();

        String content = commandParser.parseCommand(args);
        HashMap<String, Integer> result = contentParser.parseContent(content);
        resultWriter.writeResult(result);
    }
}
