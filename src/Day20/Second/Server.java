package Day20.Second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/25.
 */
public class Server {
    /**
     *  2、编写一个简单的socket通讯程序：
     *  1）、客户机程序，从控制台输入字符串，发送到服务器端，并将服务器返回的信息显示出来。
     *  2)、服务器端程序，从客户机接收数据并打印，同时将从标准输入获取的信息发送给客户机
     *  实现效果如图：
     *  图1：服务器端效果：
     
     *  图2：客户器端效果：
     */
    private ServerSocket serverSocket;
    private Socket socket;
    private Scanner scanner;
    String string;
    
    public String getString() {
        return string;
    }
    
    public Server () {
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("服务器启动");
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void method () {
        while (!"end".equalsIgnoreCase(string = get())) {
            System.out.println("    客户端说：" + string);
        }
        System.out.println("end");
        output("end");
        System.out.println("Enter结束");
        try {
            socket.close();
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //222
    private boolean message () {
        try {
            System.out.println(socket + "\t" + socket.isBound() + "\t" + socket.isConnected() + "\t" + socket.isInputShutdown() + "\t" + socket.isClosed() + "\t" + socket.getKeepAlive() + "\t" + "\t" + "\t" + "\t" + "\t" + "\t");
            //233
        } catch (Exception e) {e.printStackTrace();}
        return true;
    }
    //233

    public String get () {
        String string = "";
        try {
            if (socket == null) {
                socket = serverSocket.accept();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            string = bufferedReader.readLine();
            System.out.print("  接受到：");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public void output (String string) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("发送成功");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "客户端关闭:)");
        }
    }
    
    public static void main (String[] args) {
        Server server = new Server();
        Thread thread = new Thread(new Runnable () {
            public void run () {
                while (!"end".equalsIgnoreCase(server.getString())) {
                    System.out.println("你说点什么");
                    server.string = "";
                    try {
                        server.string = server.scanner.next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    server.output(server.string);
                }
            }
        });
        thread.start();
        server.method();
    }
}
