package Day20;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.InetAddress;
import java.net.URLConnection;

/**
 * Created by 79001 on 2017/7/24.
 */
public class Day20 {
    /**
     * 1、编写一服务器端程序，实现读取客户端发送过来的一组整数，表现为一组数与数之间用空格隔开的字符串。
     * 对这组整数进行排序处理后，返回相应的字符串给客户端，如果数据格式不正确，
     * 则返回错误信息，以本机作为服务器。
     */
    
    /**
     *  2、编写一个简单的socket通讯程序：
     *  1）、客户机程序，从控制台输入字符串，发送到服务器端，并将服务器返回的信息显示出来。
     *  2)、服务器端程序，从客户机接收数据并打印，同时将从标准输入获取的信息发送给客户机
     *  实现效果如图：
     *  图1：服务器端效果：
  
     *  图2：客户器端效果：
     */

    /**
     * 3.基于UDP，模拟两个客户端聊天。
     */
    /**
     * URL
     */
    class First {
       public void method () {
           try {
//               URLConnection urlConnection = url.openConnection();
               String string = "http://10.7.152.108:8080/j1705server/MyServlet?name=admin&pwd=12456";
               URL url = new URL(string);
               HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
               httpUrlConnection.setConnectTimeout(5000);
               httpUrlConnection.setRequestMethod("GET");
               httpUrlConnection.connect();
               System.out.println(httpUrlConnection.getResponseCode());
               System.out.println(httpUrlConnection.getResponseMessage());
               PrintStream printStream = new PrintStream(System.out);
               printStream.println(new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream())).readLine());
           } catch (Exception e) {
               System.out.println(e);
           }
       } 
    }

    public static void main (String[] args) {
        Day20 day20 = new Day20();
        First first = day20.new First();
        first.method();
    }
    
}
