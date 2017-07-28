import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by 79001 on 2017/7/28.
 */
public class myFiles {
    /**
     * 题目一：此题要求用IO流完成（共9分）
 
     【1】使用File类的方法，在D盘下创建目录myFiles,并在myFiles目录下创建一个文件分别为：info.txt。(2分)
     【2】使用IO流向info.txt中写入内容：“千峰欢迎你”。(2分)
     【3】假设本项目里面有文件image.jpg。使用IO流将该image.jpg拷贝到myFiles目录下。(3分)
     【4】设计一个方法，用于遍历该myFiles目录。(2分)
     本题目中的图片请使用文件夹中的image.jpg, 保存到项目中
     */
    //4遍历
    public void cursive (File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                if (f.isDirectory()) {
                    System.out.println("文件夹:" + f.getName());
                    cursive(f);
                }
                if (f.isFile()) {
                    System.out.println(f.getName());
                }
            }
        }
    }
    
    public static void main (String[] args) {
        File file = new File("D:\\myFiles\\info.txt");
        try {
            System.out.println("创建文件夹myFiles：" + file.getParentFile().mkdirs());
            System.out.println("创建文件info.txt：" + file.createNewFile());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("千峰欢迎你".getBytes());
        } catch (Exception e) {
            System.out.println("23 :)");
        }
        
        //3
        File inputFile = new File("C:\\Users\\79001\\Desktop\\上机题目\\image.jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file.getParentFile().getAbsolutePath() + "\\" + inputFile.getName());
            byte[] bytes = new byte[1024 * 1024];
            int n;
            while ((n = fileInputStream.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, n);
            }
            fileOutputStream.flush();
            System.out.println("复制图片成功");
        } catch (Exception e) {
            System.out.println("35 :)");
        }
    }
}
