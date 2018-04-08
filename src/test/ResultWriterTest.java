import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static org.junit.Assert.*;

/**
 * ResultWriter Tester.
 *
 * @author <SSSGLQ>
 * @since <pre>4月7日,2018</pre>
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class ResultWriterTest {
    private HashMap<String,Integer> result=new HashMap<>();
    private String sortedStr;
    private String outContent;
    private ResultWriter resultWriter=new ResultWriter();

    public ResultWriterTest(String sortedStr,
                            HashMap<String,Integer> result,String outContent){
        this.sortedStr=sortedStr;
        this.result=result;
        this.outContent=outContent;
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        //System.out.println("enter this method");
        BufferedReader bufferedReader = null;
        ArrayList<HashMap<String,Integer>> resultList = new ArrayList<>();
        ArrayList<String> sortedList=new ArrayList<>();
        ArrayList<String> outContentList=new ArrayList<>();
        //read test case from txt
        try {
            File file = new File("F:\\LearnProgrammingInformation\\java\\WcPro\\src\\test\\result_writer_testcase.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains("//")) {
                    if(!str.contains("//排序后")&&!str.contains("//输出内容")) {
                        HashMap<String, Integer> tmpMap = new HashMap<>();
                        while ((str = bufferedReader.readLine()) != null) {
                            String[] string = str.split(" ");

                            if (!str.contains("//")) {
                                if (string.length == 2) {
                                    tmpMap.put(string[0], Integer.valueOf(string[1]));
                                }
                            } else {
                                resultList.add(tmpMap);
                                break;
                            }
                        }
                    }

                    if(str.contains("//排序后")){
                        String tmpSortedStr="";
                        while ((str = bufferedReader.readLine()) != null){
                            if (!str.contains("//")) {
                                tmpSortedStr+=str+"\n";
                            }else{
                                tmpSortedStr=tmpSortedStr.substring(0,tmpSortedStr.length()-1);
                                sortedList.add(tmpSortedStr);
                                break;
                            }
                        }
                    }

                    if(str.contains("//输出内容")){
                        String tmpString="";
                        while ((str = bufferedReader.readLine()) != null){
                            if (!str.equals("")) {
                                tmpString+=str+"\n";
                            }else{
                                tmpString=tmpString.substring(0,tmpString.length()-1);
                                outContentList.add(tmpString);
                                break;
                            }
                        }
                        if(str==null){
                            tmpString=tmpString.substring(0,tmpString.length()-1);
                            outContentList.add(tmpString);
                        }
                    }
                }
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
        Object[][] result = new Object[sortedList.size()][3];
        for (int i = 0; i < sortedList.size(); i++) {
            result[i][0] = sortedList.get(i);
            result[i][1] = resultList.get(i);
            result[i][2] = outContentList.get(i);
        }

        return Arrays.asList(result);
    }

    /**
     * test method sort
     * @throws Exception
     */
    @Test
    public void testSort1() throws Exception {
        System.out.println("sort1");
        List<Map.Entry<String, Integer>> list = resultWriter.sort(result);
        String sortedContent="";
        Map.Entry<String,Integer> sortedMap;
        for(Iterator<Map.Entry<String,Integer>> it=list.iterator();it.hasNext();){
            sortedMap=it.next();
            sortedContent+=sortedMap.getKey()+" "+sortedMap.getValue()+"\n";
        }
        sortedContent=sortedContent.substring(0,sortedContent.length()-1);
        assertEquals(sortedStr,sortedContent);
    }

    /**
     * test method output
     * @throws Exception
     */
    @Test
    public void testOutput1() throws Exception {
        System.out.println("output1");
        List<Map.Entry<String, Integer>> sortedList = resultWriter.sort(result);
        String string=resultWriter.output(sortedList);
        assertEquals(outContent,string);
    }

}