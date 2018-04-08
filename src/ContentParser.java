import org.junit.Test;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wqm
 */
public class ContentParser {
    private String regEx = "[a-zA-Z]+(-[a-zA-Z]+)*";
    private Pattern pattern = Pattern.compile(regEx);

    /**
     * 输入文件内容，返回单词统计信息
     * @param content
     * @return
     */

    @Test
    public HashMap<String, Integer> parseContent(String content) {
        HashMap<String, Integer> wordMap = new HashMap<>(130);
        if (content == null || "".equals(content)) {
            return wordMap;
        }

        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (wordMap.containsKey(word)) {
                wordMap.replace(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }

        return wordMap;
    }

}
