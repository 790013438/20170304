package javadatabase.First;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    private Server() {
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("服务器启动，socket address:");
            System.out.println(serverSocket.getLocalSocketAddress());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public static Server getInstace () {
        return new Server();
    }
    
    public void method () {
        try {
            System.out.println("接受的数据");
            String enterString = receive();
            System.out.println(enterString);
            String[] stringArray = {};
            try {
                stringArray = enterString.split("\\p{Z}");
            } catch (Exception e) {
                output("输入数据有误");
            }
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < intArray.length; ++i) {
                intArray[i] = Integer.parseInt(stringArray[i]);
            }
            Arrays.sort(intArray);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < intArray.length; ++i) {
                stringBuilder.append(intArray[i] + " ");
            }
            output(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String receive () {
        String string = "";
        try {
            socket = serverSocket.accept();
            //处理输入
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            string = bufferedReader.readLine();
        } catch (Exception e) {
            System.out.println("连接客户发送失败");
            e.printStackTrace();
        }
        return string;
    }
    
    public void output (String string) {
            //处理输出
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            System.out.println("output");
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args) {
        Server server = Server.getInstace();
        server.method();
    }
}
