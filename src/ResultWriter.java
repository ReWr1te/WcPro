import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ResultWriter {
    private final String pathOfOutput="result.txt";

    /**
     * 排序类
     */
    private static class ValueComparator implements Comparator<Map.Entry<String,Integer>> {
        public int compare(Map.Entry<String,Integer> m,Map.Entry<String,Integer> n){
            if(n.getValue()-m.getValue()==0)
                return m.getKey().compareTo(n.getKey());
            else
                return n.getValue()-m.getValue();
        }
    }

    /**
     * 词频排序
     * @return
     */
    public List<Map.Entry<String,Integer>> sort(HashMap<String,Integer> hMap){
        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        list.addAll(hMap.entrySet());
        ValueComparator vc=new ValueComparator();
        Collections.sort(list,vc);
        return list;
    }

    /**
     * 使用FileWriter
     */
    private void append(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出
     */
    private void output(List<Map.Entry<String,Integer>> list){
        String outContent="";
        Map.Entry<String,Integer> oMap;
        int i=0;
        for(Iterator<Map.Entry<String,Integer>> it=list.iterator();it.hasNext()&&i<100;++i){
            oMap=it.next();
            outContent+=oMap.getKey()+" "+oMap.getValue()+"\n";
        }
        append(pathOfOutput,outContent);
    }

    //输入单词统计信息，将其写入文件
    public void writerResult(HashMap<String,Integer> result){
        List<Map.Entry<String,Integer>> list=sort(result);
        output(list);
    }
}
