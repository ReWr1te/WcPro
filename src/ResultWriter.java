import java.io.FileOutputStream;
import java.util.*;

public class ResultWriter {
    /*private final String pathOfOutput="result.txt";*/

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
     * 使用FileOutputStream覆盖写入
     */
    private void writeFile(String fileName, String content) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出
     */
    public String output(List<Map.Entry<String,Integer>> list){
        final String pathOfOutput="result.txt";
        String outContent="";
        Map.Entry<String,Integer> oMap;
        int amount=0;//仅输出单词词频从高到低排序的前100个（从1到100）
        for(Iterator<Map.Entry<String,Integer>> it=list.iterator();it.hasNext()&&amount<100;++amount){
            oMap=it.next();
            outContent+=oMap.getKey()+" "+oMap.getValue()+"\n";
        }
        if(outContent.length()>0) {
            outContent = outContent.substring(0, outContent.length() - 1);//去除输出文件末尾多余的换行符
        }
        writeFile(pathOfOutput,outContent);
        return outContent;
    }

    //输入单词统计信息，将其写入文件
    public void writeResult(HashMap<String,Integer> result){//writeResult ,not writerResult
        List<Map.Entry<String,Integer>> list=sort(result);
        output(list);
    }
}
