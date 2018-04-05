import org.junit.Test;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentParser {
    //输入文件内容，返回单词统计信息
    @Test
    public HashMap<String, Integer> parseContent(String str) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        String regEx = "[a-zA-Z]+(-[a-zA-Z]+)*";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String tmp = matcher.group().toLowerCase();
            if (hashMap.containsKey(tmp)) {
                hashMap.replace(tmp, hashMap.get(tmp) + 1);
            } else {
                hashMap.put(tmp, 1);
            }
        }

        return hashMap;
    }
}
