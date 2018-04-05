import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.*;
/** 
* ContentParser Tester. 
* 
* @author <81BlingBling18>
* @since <pre>4月 5日, 2018</pre>
* @version 1.0 
*/
@RunWith(Parameterized.class)
public class ContentParserTest { 
private String str;
private HashMap<String,Integer> result;
    private ContentParser parser = new ContentParser();

    public ContentParserTest(String str, HashMap<String, Integer> result) {
        this.str = str;
        this.result = result;
    }

    @Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

    @Parameterized.Parameters
    public static Collection parameters() {
        BufferedReader bufferedReader = null;
        ArrayList<String> stringArrayList = new ArrayList<>();
        ArrayList<HashMap<String, Integer>> resultList = new ArrayList<>();
        //read test case from txt
        try {
            File file = new File("./src/test/content_parser_testcase.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                String wordString = bufferedReader.readLine();
                String amountString = bufferedReader.readLine();
                String[] words = wordString.split(" ");
                String[] amount = amountString.split(" ");
                HashMap<String, Integer> tmpMap = new HashMap<>();
                if (words.length != amount.length) {
                    throw new Exception("test case word amount don't match");
                }
                for (int i = 0; i < words.length; i++) {
                    tmpMap.put(words[i], Integer.valueOf(amount[i]));
                }
                stringArrayList.add(str);
                resultList.add(tmpMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bufferedReader = null;
        }
        //convert test case to collection
        Object[][] result = new Object[stringArrayList.size()][2];
        for (int i = 0; i < stringArrayList.size(); i++) {
            result[i][0] = stringArrayList.get(i);
            result[i][1] = resultList.get(i);
        }

        return Arrays.asList(result);
    }
/** 
* 
* Method: parseContent(String str) 
* 
*/ 
@Test
public void testParseContent() throws Exception { 
//TODO: Test goes here...
    HashMap<String, Integer> hashMap = parser.parseContent(str);
    assertEquals(hashMap, result);

} 


} 
