package javagame.threads;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/19.
 */
public class Day17 {
    /**
     * 1、取100个随机数，写出到本地文件中，加序号，每写一个换行一次。
     * 第1-50次:随机产生[0.0-1.0)double数，
     * 第51-100次：产生[15-50]的整数
     * 知识点：IO字符流BufferedWriter/FileWriter
     */
    class First {
        public void method () {
            File file = new File("f://qwerty.txt");
            try {
                PrintStream printStream = new PrintStream(file);
                Random random = new Random();
                for (int i = 0; i < 50; ++i) {
                    printStream.println(random.nextDouble());
                }
                for (int i = 0; i < 50; ++i) {
                    printStream.println(random.nextInt(36) + 15);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    /**
     * 2、有五个学生，每个学生有3门课的成绩，从键盘输入以上数据（包括学生号，姓名，三门课成绩），计算出平均成绩，将原有的数据和计算出的平均分数存放在磁盘文件"stud"中。
     * 知识点：IO字符流
     */
    class Second {
        public void method () {
            Student[] students = new Student[3];
            
            Scanner scanner = new Scanner(System.in);
            for (int i = 0 ; i < students.length; ++i) {
                try {
                    System.out.println("请输入学生学号");
                    String id = scanner.next();
                    System.out.println("输入学生姓名");
                    String name = scanner.next();
                    System.out.println("输入学生成绩");
                    System.out.println("课程1");
                    double lesson1 = scanner.nextDouble();
                    System.out.println("课程2");
                    double lesson2 = scanner.nextDouble();
                    System.out.println("课程3");
                    double lesson3 = scanner.nextDouble();
                    students[i] = new Student(id, name, lesson1, lesson2, lesson3);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            StringBuilder stringBuilder = new StringBuilder();
            String newLine = String.format("%n");
            for (int i = 0; i < 3; ++i) {
                stringBuilder.append(students[i]).append(students[i].averageScore()).append(newLine);
            }
            
            File file = new File("f:\\qwerty.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(stringBuilder.toString());
                fileWriter.flush();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    fileWriter.close();
                } catch (Exception e) {}
            }
        }
    }
    
    class Student {
        private String id;
        private String name;
        private double lesson1;
        private double lesson2;
        private double lesson3;
        
        public double averageScore () {
            double sum = 0.0;
            sum += lesson1 + lesson2 + lesson3;
            return sum / 3;
        }
    
        public Student(String id, String name, double lesson1, double lesson2, double lesson3) {
            this.id = id;
            this.name = name;
            this.lesson1 = lesson1;
            this.lesson2 = lesson2;
            this.lesson3 = lesson3;
        }
    
        public double getLesson1() {
            return lesson1;
        }
    
        public double getLesson2() {
            return lesson2;
        }
    
        public double getLesson3() {
            return lesson3;
        }
    
        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", lesson1=" + lesson1 +
                    ", lesson2=" + lesson2 +
                    ", lesson3=" + lesson3 +
                    '}';
        }
    }
    
    /**
     * 3、模拟记录用户登录日志(采用追加方式记录)，从控制台接收用户输入的用户名和密码,在文件中记录用户名和密码以及登录时间, 格式如下: name=zhangsan,pwd=123456,时间=2014-06-05 18:22:33
     * 知识点：IO字符流、Date类、SimpleDateFormat
     */
    class Third {
        public void method () {
            System.out.println("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            System.out.println("请输入密码：");
            String password = scanner.next();
            
            File file = new File("f:\\qwerty.txt");
            try {
                PrintStream printStream = new PrintStream(file);
                StringBuilder stringBuilder = new StringBuilder();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                stringBuilder.append("name = ").append(name).append(", pwd = ").append(password).append(", 时间 = ").append(simpleDateFormat.format(new Date()));
                printStream.println(stringBuilder.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * 4、写一段文字，加密后（只要与你输入的不一样就可以）存入一个隐藏的TXT文档中，然后读出来（输出当初输入时候的内容）。
     * 加密方式可以自己定义
     * 知识点：IO字符流
     */
    class Fourth {
        public void method () {
            File file = new File("F:" + File.separator + "w.txt");
            try {
                file.createNewFile();
                // R ： 只读文件属性。A：存档文件属性。S：系统文件属性。H：隐藏文件属性。
                String sets = "attrib +H \"" + file.getAbsolutePath() + "\"";
                System.out.println(sets);
                // 运行命令
                Runtime.getRuntime().exec(sets);
    
                Base64.Encoder encoder = Base64.getEncoder();
                String string = encoder.encodeToString("写一段文字，加密后存入一个隐藏的TXT文档中，然后读出来".getBytes("UTF-8"));
                OutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(string.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
    
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                Base64.Decoder decoder = Base64.getDecoder();
                System.out.println(new String(decoder.decode(bufferedReader.readLine().getBytes("UTF-8"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 5、模拟聊天，保存记录
     * 运行结果
     *
     * 文件内容：
     * 2016年9月23日
     * 09:56:40
     * 小明:hi
     * 09:56:53
     * 小红:你好，我是客服小红，请问有什么可以帮助你的？
     * 09:57:03
     * 小明:你长得漂亮吗?
     * 09:57:10
     * 小红:我觉得一般
     * 共5条记录
     * 知识点：IO流、File类、Date类、SimpleDateFormat类
     */
    class Fifth {
        public void method () {
            File file = new File("f:\\qwerty.txt");
            String newLine = String.format("%n");
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("5、模拟聊天，保存记录" + newLine +
                        "运行结果" + newLine +
                        newLine +
                        "文件内容：" + newLine +
                        "2016年9月23日" + newLine +
                        "09:56:40" + newLine +
                        "小明:hi" + newLine +
                        "09:56:53" + newLine +
                        "小红:你好，我是客服小红，请问有什么可以帮助你的？" + newLine +
                        "09:57:03" + newLine +
                        "小明:你长得漂亮吗?\n" + newLine +
                        "09:57:10\n" + newLine +
                        "小红:我觉得一般\n" + newLine +
                        "共5条记录\n" + newLine +
                        "知识点：IO流、File类、Date类、SimpleDateFormat类");
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void main (String[] args) {
        Day17 day17 = new Day17();
        
//        Server first = day17.new Server();
//        first.method();
        
//        Second second = day17.new Second();
//        second.method();
        
//        Third third = day17.new Third();
//        third.method();
        
//        Fourth fourth = day17.new Fourth();
//        fourth.method();
        
        Fifth fifth = day17.new Fifth();
        fifth.method();
    }
    
}
