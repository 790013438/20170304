package Day20.Second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/25.
 */
public class Client {
    /**
     *  2、编写一个简单的socket通讯程序：
     *  1）、客户机程序，从控制台输入字符串，发送到服务器端，并将服务器返回的信息显示出来。
     *  2)、服务器端程序，从客户机接收数据并打印，同时将从标准输入获取的信息发送给客户机
     *  实现效果如图：
     *  图1：服务器端效果：
     
     *  图2：客户器端效果：
     */
    private Socket socket;
    private Scanner scanner;
    String string;
    
    public Client () {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 7777);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);
    }
    
    public void send (String string) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(string + String.format("%n"));
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("发送成功");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "服务器关闭:)");
        }
    }
    
    public void method () {
        while (!"end".equalsIgnoreCase(string)) {
            System.out.println("发送点什么吧");
            string = "";
            try {
                string = scanner.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
            send(string);
        }
    }
    
    public String receive () {
        String string = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            string = bufferedReader.readLine();
            System.out.print("  接受到：");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "服务器终端连接:)");
            System.exit(-1);
        }
        return string;
    }

    public static void main (String[] args) {
        Client client = new Client();
        Thread thread = new Thread(new Runnable() {
            public void run () {
                while (!"end".equalsIgnoreCase(client.string = client.receive())) {
                    System.out.println("    服务器说：" + client.string);
                }
                System.out.println(client.string);
                client.send("end");
                try {
                    client.socket.close();
                    client.scanner.close();
                } catch (Exception e) {
                    System.out.println(89 + ":)");
                }
            }
        });
        thread.start();
        client.method();
    }
}
