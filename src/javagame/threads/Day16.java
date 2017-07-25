package javagame.threads;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/18.
 */
public class Day16 {
    /**
     * 1，列出指定目录中所有的子文件名与所有的子目录名。
     */
    class First {
        
        public void method () {
            File file = new File("D:\\file\\java_workspace");
            recursive(file, "");
        }
        
        public void recursive (File file, String tab) {
            if (file.listFiles() != null) {
                for (File f : file.listFiles()) {
                    System.out.print(tab);
                    System.out.println(f);
                    if (file.isDirectory()) {
                        recursive(f, tab + "\t");
                    }
                }
            }
        }
    }
    
    /**
     * 2，列出指定目录中所有的子文件名与所有的子目录名，要求目录名与文件名分开列出，格式如下：
     * 子目录：
     * ...
     * ...
     * 子文件：
     * ...
     * ...
     */
    class Second {
        public void method () {
            List<File> dirArrayList = new ArrayList<>();
            List<File> fileArrayList = new ArrayList<>();
            File file = new File("C:\\Users\\Public");
            recursive(file, dirArrayList, fileArrayList);
            System.out.println("子目录:");
            for (File f : dirArrayList) {
                System.out.println("\t" + f);
            }
            System.out.println("子文件:");
            for (File f : fileArrayList) {
                System.out.println("\t" + f);
            }
        }
        
        public void recursive (File file, List<File>dirArrayList, List<File>fileArrayList) {
            if (file.listFiles() != null) {
                for (File f : file.listFiles()) {
                    if (f.isDirectory()) {
                        dirArrayList.add(f);
                        recursive(f, dirArrayList, fileArrayList);
                    } else {
                        fileArrayList.add(f);
                    }
                }
            }
        }
    }
    
    /**
     * 3，列出指定目录中所有扩展名为.java的文件。
     */
    class Third {
        public void method () {
            File file = new File("d:\\file\\java_workspace");
            recursive(file);
        }
        
        public void recursive (File file) {
            if (file.listFiles() != null) {
                for (File f : file.listFiles()) {
                    if (f.getName().matches("[\\w]+\\.java")) {
                        System.out.println(f);
                    }
                    if (f.isDirectory()) {
                        recursive(f);
                    }
                }
            }
        }
        
    }
    
    /**
     * 4，写代码实现把"Hello World!"写到"c:/a.txt"文件中。
     * a, c:/a.txt不存在时，测试一下。
     * b, c:/a.txt存在时，也测试一下。
     * 要写两个版本：
     * a, 使用write(int b) 实现。
     * b, 使用write(byte[] b) 实现。
     */
    class Fourth {
    
        public void method () {
            String string = "\"Hello World!\"";
            
            File file = new File("d:\\a.txt");
            
            //* a, c:/a.txt不存在时，测试一下。
            System.out.println(file.getParentFile().mkdirs());
            try {
                if (file.createNewFile()) {
                    System.out.println(file.getName() + "创建成功");
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                
                //* a, 使用write(int b) 实现。
                for(int i = 0; i < string.length(); ++i) {
                    fileOutputStream.write((int)string.charAt(i));
                }
                
                //* b, 使用write(byte[] b) 实现。
                fileOutputStream.write(String.format("%n%s", string).getBytes());
                System.out.println("写入成功");
            } catch (IOException e) {
                System.out.println(file.getName() + "创建失败");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * 5,从键盘读取一个路径，判断这个路径是否存在，如果存在则输出这个完整路径。如果不存在则创建这个路径。
     */
    class Fifth {
        
        public void method () {
            String enterString = "d:\\";
            try {
                Scanner scanner = new Scanner(System.in);
                enterString = scanner.next();
            } catch (Exception e) {
                System.out.println(e);
            }
            File file = new File(enterString);
            if (file.exists()) {
                System.out.println(file.getAbsoluteFile() + " 存在");
            } else {
                if (file.mkdirs())
                    System.out.println(file.getAbsoluteFile() + " 创建成功");
                else
                    System.out.println(file.getAbsoluteFile() + "创建失败");
            }
        }
        
    }
    
    /**
     * 6，文件“我的青春谁做主.txt”位于D盘根目录下，要求将此文件的内容复制到    C:\myFile\my Prime.txt中
     */
    class Sixth {
        
        public void method () {
            cp(new File("我的青春谁做主.txt"), new File("C:\\myFile\\my Prime.txt"));
        }
        
        public void cp (File srcFile, File desFile) {
            if (!srcFile.exists()) {
                System.out.println("不存在 " + srcFile.getAbsolutePath());
                return;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(srcFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                
                desFile.getParentFile().mkdirs();
                desFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(desFile);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                
                bufferedOutputStream.write(bufferedInputStream.read());
                bufferedOutputStream.flush();
                System.out.println("复制完成");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }
    
    /**
     * 7，使用字符流读取一个字符文件，并把其中的所有小写字母变成大写字母，然后写入到另外一个文件中。
     */
    class Seventh {
        public void method () {
            String enterString = "d:\\";
            
            enterString = "20170304\\src\\javagame\\threads\\Day16.java";
            
            File file = new File(enterString);
            try {
                FileReader fileReader= new FileReader(file);
                int n;
                while((n = fileReader.read()) > 0) {
                    System.out.print(Character.toUpperCase((char)n));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void main (String[] args) {
        Day16 day16 = new Day16();
        
//        Server first = day16.new Server();
//        first.method();
        
//        Second second = day16.new Second();
//        second.method();
        
//        Third third = day16.new Third();
//        third.method();
        
//        Fourth fourth = day16.new Fourth();
//        fourth.method();
        
//        Sixth sixth = day16.new Sixth();
//        sixth.method();
        
        Seventh seventh = day16.new Seventh();
        seventh.method();
    }
    
}
