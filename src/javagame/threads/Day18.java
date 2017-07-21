package javagame.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by 79001 on 2017/7/20.
 */
public class Day18 implements Serializable {
    /**
     * 1.自己定义一个Student类,里面有一些属性
     * 创建若干个Student对象,装入ArrayList
     * 将ArrayList遍历,把每一个Student对象序列化到文件中,
     * 从文件中循环读取Student对象,再封装到另外一个ArrayList里面
     * 遍历新的ArrayList,输出每个Student对象
     * 知识点：IO流对象流、集合List
     */
    class First {
        public void method () {
            //* 创建若干个Student对象,装入ArrayList
            Student[] students = new Student[3];
            ArrayList<Student> arrayList = new ArrayList();
            for (int i = 0; i < students.length; ++i) {
                students[i] = new Student("qwerty", 18 + i);
                arrayList.add(students[i]);
            }
//            * 将ArrayList遍历,把每一个Student对象序列化到文件中,
            File file = new File("f:\\qwerty.txt");
            OutputStream outputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                objectOutputStream = new ObjectOutputStream(outputStream);
                Iterator<Student> iterator = arrayList.iterator();
                while (iterator.hasNext()) {
                    objectOutputStream.writeObject(iterator.next());
                }
                objectOutputStream.flush();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    objectOutputStream.close();
                    outputStream.close();
                } catch (Exception e) {}
            }
//            * 从文件中循环读取Student对象,再封装到另外一个ArrayList里面
//                    * 遍历新的ArrayList,输出每个Student对象
            try {
                InputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Object object;
                arrayList.clear();
                while((object = objectInputStream.readObject()) != null) {
                    arrayList.add((Student)object);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            for (Student stu : arrayList) {
                System.out.println(stu);
            }
        }
    }
    
    class Student implements Serializable {
        private String name;
        private int age;
    
        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Student student = (Student) o;
        
            if (age != student.age) return false;
            return name != null ? name.equals(student.name) : student.name == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }
    
        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    
    /**
     * 2 创建一个Student类sno（学号），name，score：创建3个该类的对象：将其信息保存到配置文件中（properties文件），再读取出来
     * 知识点：properties
     */
    class Second {
        public void method () {
            Student[] students = new Student[3];
            char ch = '三';
            for (int i = 0; i < 3; ++i) {
                students[i] = new Student("张" + ch++, 20 + i);
            }
            Properties properties = new Properties();
            for (int i = 0; i < students.length; ++i) {
                properties.setProperty(students[i].getName(), students[i].toString());
            }
//            写
            File file = new File("f:\\qwerty.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                properties.store(outputStreamWriter, "qwerty");
//    读
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                properties.clear();
                properties.load(inputStreamReader);
                System.out.println(properties.get("张三"));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
 
    /**
     * 3、文件中保存hello world,使用RandomAccessFile对象，读取文件中的world内容
     * 知识点：RandomAccessFile
     */
    class Third {
        public void method () {
            File file = new File("f:\\qwerty.txt");
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                randomAccessFile.skipBytes(6);
                System.out.println(randomAccessFile.readLine());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * 4、把一个mp3分分割成1MB一个文件，在进行整合
     * 知识点：SequenceInputStream
     */
    class Fourth {
        public void method () {
            File file = new File("f:\\張國榮 - 路過蜻蜓 (MTV).mp3");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                
                int n;
                String[] names = file.getAbsolutePath().split("\\.");
                byte[] bytes = new byte[1024 * 1024];
                int i = 0;
                while ((n = fileInputStream.read(bytes)) > 0 ) {
                    FileOutputStream fileOutputStream = new FileOutputStream(names[0] + "-" + i++ + "." + names[1]);
                    System.out.println(n);
                    //233
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
 
    /**
     * 5、Home01.java将若干个Student对象,若干个Teacher对象,写出到d:/0404/aa.bb中
     * Home02.java将该文件中所有Student对象反序列化回来装入List,所有的Teacher对象反序列化回来转入另外一个List
     * 知识点：IO流对象流、集合List
     */
    
    public static void main (String[] args) {
        Day18 day18 = new Day18();
        
//        First first = day18.new First();
//        first.method();
        
//        Second second = day18.new Second();
//        second.method();
    
//        Third third = day18.new Third();
//        third.method();
        
        Fourth fourth = day18.new Fourth();
        fourth.method();
        
    }
    
}


