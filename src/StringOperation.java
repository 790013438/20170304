import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by 79001 on 2017/7/28.
 */
public class StringOperation {
    /**
     * 题目二：String操作完成（共8分）
     文本文件中存储多个文章标题、作者，标题和作者之间用若干个空格（数量不定）隔开，每行一个，标题有长有短，输出到控制台的时候最多标题长度为10，若超过10则只截取前半部分并在最后添加“...”，加上竖线后输出作者名字。
     【1】读取文件内容 （2分）
     【2】处理空格（3分）
     【3】处理标题（3分）
     */
    public static void main (String[] args) {
        File file = new File("C:\\Users\\79001\\Desktop\\上机题目\\test2.txt");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string;
            ArrayList<String> arrayList = new ArrayList();
            while ((string = bufferedReader.readLine()) != null) {
                if (string.length() > 10) {
                    String[] array = string.split("[\\p{Z}s\t]");
                    System.out.println(array[0].substring(0, 10) + "...|" + array[1]);
                } else {
                    System.out.println(string.replaceAll("[\\p{Z}s\t]+", "|"));
                }
            }
        } catch (Exception e) {
            System.out.println("21 :)");
            e.printStackTrace();
        }
    }
}
