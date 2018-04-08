import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * ResultWriter Class.
 *
 * @author <SSSGLQ>
 * @since <pre>4月1日,2018</pre>
 */
public class ResultWriter {
    /*private final String pathOfOutput="result.txt";*/

    /**
     * 排序类
     */
    private static class ValueComparator implements Comparator<Map.Entry<String,Integer>> {
        @Override
        public int compare(Map.Entry<String,Integer> m,Map.Entry<String,Integer> n){
            if(n.getValue()-m.getValue()==0) {
                return m.getKey().compareTo(n.getKey());
            } else {
                return n.getValue() - m.getValue();
            }
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
     * 使用FileOutputStream覆盖写入
     */
    private void writeFile(String fileName, String content) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            fileOutputStream=null;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 输出
     */
    public String output(List<Map.Entry<String,Integer>> list){
        final String pathOfOutput="result.txt";
        //规定输出的单词排序结果最多为100个
        final int fullAmount=100;
        String outContent="";
        Map.Entry<String,Integer> oMap;
        //仅输出单词词频从高到低排序的前100个（从1到100）
        int amount=0;
        for(Iterator<Map.Entry<String,Integer>> it=list.iterator();it.hasNext()&&amount<fullAmount;++amount){
            oMap=it.next();
            outContent+=oMap.getKey()+" "+oMap.getValue()+"\n";
        }
        if(outContent.length()>0) {
            //去除输出文件末尾多余的换行符
            outContent = outContent.substring(0, outContent.length() - 1);
        }
        writeFile(pathOfOutput,outContent);
        return outContent;
    }

    /**
     * 输入单词统计信息，将其写入文件
     */
    public void writeResult(HashMap<String,Integer> result){//writeResult ,not writerResult
        List<Map.Entry<String,Integer>> list=sort(result);
        output(list);
    }
}
