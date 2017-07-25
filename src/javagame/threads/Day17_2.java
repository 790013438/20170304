package javagame.threads;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 79001 on 2017/7/19.
 */
public class Day17_2 implements Serializable {
    /**
     * 1、读取模板文件C:\pet.templater的内容，按照pet.template的模板格式保存宠物数据到文本文件，即把{name}、{type}、{master}替换为具体的宠物信息，将替换后的内容写入到D\myDoc\pet.txt中
     */
    class First {
        public void method () {
            File file = new File("F:\\qwerty.txt");
            InputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String string;
                while((string = bufferedReader.readLine()) != null) {
                    string = string.replace("{name}", "欧欧");
                    string = string.replace("{type}", "狗狗");
                    string = string.replace("{master}", "李伟");
                    System.out.println(string);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    bufferedInputStream.close();
                    fileInputStream.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
 
    /**
     * 2、假设一个文件中有如下内容，请把这些内容读入内存，并存入List<WorldCup>集合中。 举办时间、举办国、夺冠国
     * 1930,乌拉圭,乌拉圭
     * 1934,意大利,意大利
     * 1938,法国,意大利
     * 1950,巴西,乌拉圭
     * 1954,瑞士,德国
     * 1958,瑞典,巴西
     * 1962,智利,巴西
     * 1966,英格兰,英格兰
     * 1970,墨西哥,巴西
     * 1974,德国,德国
     * 1978,阿根廷,阿根廷
     * 1982,西班牙,意大利
     * 1986,墨西哥,阿根廷
     * 1990,意大利,德国
     * 1994,美国,巴西
     * 1998,法国,法国
     * 2002,韩国和日本,巴西
     * 2006,德国,意大利
     * 2010,南非,西班牙
     * 2014,巴西,德国
     */
    class Second {
        public void method () {
            List<WorldCup> arrayList = new ArrayList();
            File file = new File("F:\\qwerty.txt");
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String string;
                while ((string = bufferedReader.readLine()) != null) {
                    try {
                        String[] array = string.split(",");
                        arrayList.add(new WorldCup(array[0], array[1], array[2]));
                    } catch (Exception e) {}
                }
                System.out.println(arrayList);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    class WorldCup implements Serializable {
        private String time;
        private String host;
        private String win;
    
        WorldCup(String time, String host, String win) {
            this.time = time;
            this.host = host;
            this.win = win;
        }
    
        @Override
        public String toString() {
            return "WorldCup{" +
                    "time='" + time + '\'' +
                    ", host='" + host + '\'' +
                    ", win='" + win + '\'' +
                    '}';
        }
    }
    
    /**
     * 3、模拟用户登陆，并记录登陆日志。
     * a：根据用户的选择是注册用户还是登陆用户。
     * b：如果是用户注册把用户注册的用户名和密码保存在文件中，文件可以记录多组用户名和密码。
     * c：每个用户每登陆一次都生产一个登陆记录，把登陆记录保存在文件中，形成登陆日志。登陆记录格式(用户名, 密码, 登陆时间)： zhangsan, xxxxx, 2016年8月16日 13:20:20
     * d：如果是用admin管理员的身份登陆的，可以查看所有的用户的登陆记录。否则其他用户只可以查看自己的登陆记录。
     */
    
    /**
     * ObjectInputStream
     */
    class Fourth {
       public void method () {
           File file = new File("f:\\qwerty.txt");
           try {
               System.out.println(file.createNewFile());
               FileOutputStream fileOutputStream = new FileOutputStream(file);
               ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
               objectOutputStream.writeObject(new WorldCup("1234", "qwerty", "qwerty"));
           } catch (Exception e) {
               System.out.println(e);
               e.printStackTrace();
           }
       }
    }
    
    /**
     * Properties
     */
    class Sixth {
        public void method () {
            Properties properties = new Properties();
            properties.setProperty("qwerty", "123456");
            System.out.println(properties);
            
            File file = new File("F://qwerty.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                properties.store(outputStreamWriter, "keyboard");
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }
    
    /**
     * SequenceInputStream
     */
    class Seventh {
        public void method () {
            File file = new File("f:\\qwerty.txt");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileInputStream wInputStream = new FileInputStream("f:\\fragment.txt");
                SequenceInputStream sequenceInputStream = new SequenceInputStream(fileInputStream, wInputStream);
                byte[] bytes = new byte[1024];
                int n = 0;
                FileOutputStream fileOutputStream = new FileOutputStream("f:\\w.txt");
                while ((n = sequenceInputStream.read(bytes)) > 0) {
                    
                    fileOutputStream.write(bytes, 0, n);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * ByteBuffer
     */
    class Eighth {
        public void method () {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            byteBuffer.put((byte)10);
            byteBuffer.put((byte)11);
            byteBuffer.put((byte)12);
            byteBuffer.put(new byte[]{13, 14});
            byteBuffer.flip();
            for (int i = 0; i < byteBuffer.limit(); ++i) {
                System.out.println(byteBuffer.get(i));
            }
        }
    }
    
    /**
     * FileChanel
     */
    class Ninth {
        public void method () {
        
        }
    }
    
    /**
     * MappedByteBuffer
     */
    class Tenth {
        public void method () {
//            MappedByteBuffer mappedByteBuffer = new MappedByteBuffer(MappedByteBuffer.)
        }
    }
    
    public static void main (String[] args) {
        Day17_2 day17 = new Day17_2();
        
//        Server first = day17.new Server();
//        first.method();
    
//       Fourth fourth = day17.new Fourth();
//       fourth.method();
    
//        Sixth sixth = day17.new Sixth();
//        sixth.method();
        
//        Seventh seventh = day17.new Seventh();
//        seventh.method();
        
        Eighth eighth = day17.new Eighth();
        eighth.method();
    }
    
}
