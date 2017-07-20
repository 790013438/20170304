package javagame.threads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/18.
 */
public class Day16_2 {
    /**
     * 1, 从键盘输入一个文件名，在D:盘查找这个文件，如果找到就输出这个文件的绝对路径，如果找不到则输出找不到这个文件。
     */
    class First {
        public void method () {
            String enterString = "D:\\";
            try {
                System.out.println("请输入一个文件名：");
                Scanner scanner = new Scanner(System.in);
                enterString = scanner.next();
            } catch (Exception e) {
                System.out.println(e);
            }
            File file = new File(enterString);
            recursive(file);
        }
        
        public void recursive (File file) {
            File f = new File("d:\\");
            if (f.listFiles() != null) {
                for (File eachFile : f.listFiles()) {
                    if (eachFile.equals(file)) {
                        System.out.println(eachFile.getAbsoluteFile() + " 找到了目标文件");
                    }
                }
            }
        }
    }
    
    /**
     * 2,删除指定目录及其子目录下所有不是以.txt结尾的文件
     */
    class Second {
        public void method () {
            String enterString = "d:\\";
            try {
                System.out.println("请输入一个目录，删除指定目录及其子目录下所有不是以.txt结尾的文件");
                Scanner scanner = new Scanner(System.in);
                enterString = scanner.next();
            } catch (Exception e) {
                System.out.println(e);
            }
            File file = new File(enterString);
            recursive(file);
        }
        
        public void recursive (File file) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile() && !f.getName().matches("\\w+\\.txt")) {
                        file.delete();
                    } else {
                        recursive(f);
                    }
                }
            }
        }
    }
    
    /**
     * 3,如果c:/a.txt文本文件不存在，则创建文件并写入“Java IO”, 如果存在则文件中追加内容：“Java IO”。
     */
    class Third {
        public void method () {
            File file = new File("c:\\a.txt");
            FileOutputStream fileOutputStream = null;
            
            try {
                if (file.exists()) {
                    fileOutputStream = new FileOutputStream(file, true);
                } else {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                }
                
                fileOutputStream.write("Java IO".getBytes());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
     
    /**
     * 4，随机生成100个 [100,200]的整数，并写入到文件中。每5个换一行。
     */
    class Fourth {
        public void method () {
            int[] intArray = new int[100];
            Random random = new Random();
            
            //生成随机数
            for (int i = 0; i < intArray.length; ++i) {
                intArray[i] =random.nextInt(101) + 100;
            }
            StringBuilder stringBuilder = new StringBuilder();
            
            //转成字符串
            int i = 0;
            while (i < intArray.length) {
                for (int j = 0; j < 5; ++j) {
                    stringBuilder.append(intArray[i]).append(", ");
                    i++;
                }
                stringBuilder.append(String.format("%n"));
            }
            
            //写入
            File file = new File("d:\\c.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(stringBuilder.toString().getBytes());
                System.out.println("写入完成");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * 5，编写程序来实现如下功能
     * a：在C盘下创建一个目录Letter
     * b：在控制台显示下列选项：1 查看请假条 2 撰写请假条
     * c：如果用户选择2，则提示用户撰写请假条，并把撰写的内容存入到Letter文件夹下。
     * 格式如下：
     * 请假人：王宝强
     * 请假日期：2016年8月15日
     * 请假原因：向法院起诉马蓉离婚.....先请假一天等等
     */
    class Fifth {
        public void method () {
            File file = new File("C:\\Letter\\leave.txt");
            System.out.println(file.getParentFile().mkdirs());
            try {
                //创建好文件
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void main (String[] args) {
        Day16_2 day16 = new Day16_2();
        Fourth fourth = day16.new Fourth();
        fourth.method();
    }
    
}
