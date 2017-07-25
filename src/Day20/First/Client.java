package Day20.First;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by 79001 on 2017/7/24.
 */
public class Client {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private OutputStreamWriter outputStreamWriter;
    
    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 7777);
            //输出
            System.out.println(socket);
        } catch (Exception e) {
            System.out.println("connection fail");
            System.exit(-1);
        }
    }
    
    public void method() {
        String outString = "12 98 54 58 78 32";
        System.out.println("要输入的数据:");
        System.out.println(outString);
        try {
            output(outString);
            System.out.println("结果");
            System.out.println(receive());
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }
    }
    
    public String receive () {
        String string = "";
        try {
            //处理输入
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            string = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return string;
    }
    
    public void output (String string) {
        //处理输出
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//            outputStreamWriter.write(string);
//            outputStream.flush();
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args) {
        Client client = new Client();
        client.method();
    }
}