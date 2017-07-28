package javagame.prototype;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 79001 on 2017/7/25.
 */
public class Day21 {
    /**
     * 作业1
     *  创建一个工具类，提供静态的方法，用于获取网络数据。
     *  public static void saveDataToDisk(String baseUrl) throws Exception
     *  知识点: HttpURLConnection
     */
    static class First {
        public void method () {
            try {
                First.saveDataToDisk("www.baidu.com");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        public static void saveDataToDisk(String baseUrl) throws Exception {
            URL url = new URL(baseUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            byte[] bytes = new byte[1024];
            File file = new File("f:\\" + baseUrl.substring(baseUrl.length() - 5, baseUrl.length()  ));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int n;
            while ((n = bufferedInputStream.read(bytes)) > 0) {
                bufferedOutputStream.write(bytes, 0, n);
            }
        }
    }
    /**
     * 作业2
     *  创建静态的方法，用于HttpCLient操作get请求，登录服务器
     *  public static String loginGet(String baseUrl, String params)
     *  throws Exception
     *  创建静态的方法，用于HttpCLient操作post请求，登录服务器
     *  public static String loginPost(String baseUrl, String params)
     *  throws Exception
     *  知识点:HttpURLConnection
     */
    static class Second {
        public void method () {

        }
    
        public static String loginGet (String baseUrl, String params) throws Exception {
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            //"http://targethost/homepage"
            HttpGet httpGet = new HttpGet(baseUrl + "?" +params);
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            HttpEntity httpEntity = null;
            try {
                System.out.println(closeableHttpResponse.getStatusLine());
                httpEntity = closeableHttpResponse.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                try {
                    EntityUtils.consume(httpEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                try {
                    closeableHttpResponse.close();
                } catch (Exception e) {e.printStackTrace();}
            }
            return new String(EntityUtils.toByteArray(httpEntity));
        }
    
        public static String loginPos (String baseUrl, String params) throws Exception {
            CloseableHttpClient closeableHttpclient = HttpClients.createDefault();
//            "http://targethost/login"
            HttpPost httpPost = new HttpPost(baseUrl);
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = closeableHttpclient.execute(httpPost);
            String string = "";
            try {
                System.out.println(response.getStatusLine());
                HttpEntity httpEntity = response.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(httpEntity);
                string = new BufferedReader(new InputStreamReader(httpEntity.getContent())).readLine();
            } finally {
                response.close();
            }
            return string;
        }
    }

    /**
     * 作业3
     *  使用HttpClient 保存网络中一张图片
     *  知识点: HttpCLient
     */
    class Third {
        public void method () {
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("www.baidu.com");
            try {
                CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
                System.out.println(closeableHttpResponse.getStatusLine());
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpEntity.getContent());
                //file
                File file = new File("F:\\qwerty");
                file.createNewFile();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                int n;
                byte[] bytes = new byte[1024];
                while ((n = bufferedInputStream.read(bytes)) > 0) {
                    bufferedOutputStream.write(bytes, 0, n);
                }
            } catch (Exception e) {
                System.out.println("141 :)");
            }
        }
    }
    
    class Fourth {
        public void mehtod () {
        
        }
        
        public void document () {
            SAXReader saxReader = new SAXReader();
            Document document = null;
            try {
                document = saxReader.read(new File("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\pet.xml"));
            } catch (Exception e) {
                System.out.println("174:)");
            }
            Iterator iterator = document.nodeIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
        
        public void element () {
            //Simple Api for Xml
            SAXReader saxReader = new SAXReader();
            Document document = null;
            try {
                document = saxReader.read(new File("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\pet.xml"));
            } catch (Exception e) {
                System.out.println("188 file error :)");
                System.exit(-1);
            }
            Element element = document.getRootElement();
            Iterator<Node> iterator = element.nodeIterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();
//                System.out.println(node.asXML());//原文
//                System.out.println(node.getText());//空格
//                System.out.println(node.getName());//tag name
//                System.out.println(node.getNodeTypeName());//element, text
            }
            System.out.println(element.element("dog").getName() + "\t" + element.element("dog").getStringValue());
        }
        
        public void print(Element element) {
            Iterator<Node> iterator = element.nodeIterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();
                if ("Element".equals(node.getNodeTypeName())) {
                    System.out.println(node.getName());
                }
            }
        }
        
        public void weather () {
            SAXReader saxReader = new SAXReader();
        }
        
    }
    
    class Dom4j {
        public void method () {
            SAXReader saxReader = new SAXReader();
            Document document = null;
            try {
                document = saxReader.read(new File("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\pet.xml"));
            } catch (Exception e) {
                System.out.println("227 file error :)");
            }
            Element element = document.getRootElement();
            System.out.println(element.element("snake").elementText("name"));
        }
    }
    
    /**
     * SAX
     */
    class Fifth {
        public void method () {
            try {
                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                saxParser.parse("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\pet.xml", new DefaultHandler() {
                    @Override
                    public void startElement (String url, String localName, String qName, Attributes attributes) {
//                        System.out.println(localName);
                        for (int i = 0; i < attributes.getLength(); ++i)
                            System.out.println(attributes.getQName(i) + attributes.getValue(i));
                    }
                    
                    @Override
                    public void characters (char charArray[], int startIndex, int length) {
                        String string = new String(charArray, startIndex, length);
                        string = string.trim();
                        if (!"".equals(string)) {
                            System.out.println(string);
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println(":)");
            }
        }
    }
    
    public static void main (String[] args) {
        Day21 day21 = new Day21();
        
        First first = new First();
        try {
            First.saveDataToDisk("");
        } catch (Exception e) {
            System.out.println("276 :)");
        }
    }

}
