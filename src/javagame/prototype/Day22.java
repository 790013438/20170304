package javagame.prototype;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 79001 on 2017/7/26.
 */
public class Day22 {
    /**
     * 作业1
     * 解析文件夹中的三个xml文件
     *
     * 使用三种方法，将xml中的信息封装到集合中
     */

    class First {
        public void method () {
            SAXReader saxReader = new SAXReader();
            try {
                Document document = saxReader.read(new File("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\city.xml"));
                Element element = document.getRootElement();
                List<Element> list = element.elements("province");
                List<Province> provinceArrayList = new ArrayList<>();
                for (int i = 0; i < list.size(); ++i) {
                    Element provinceElement = list.get(i);
                    System.out.println(provinceElement.attribute(0).getStringValue());
                    List<Element> cityList = provinceElement.elements("city");
                    List<City> cityArrayList = new ArrayList<>();
                    for (int j = 0; j < cityList.size(); ++j) {
                        Element cityElement = cityList.get(j);
                        System.out.println(cityElement.attribute(0).getStringValue());
                        List<Element> districtList = cityElement.elements("district");
                        List<District> districtArrayList = new ArrayList<>();
                        for (int x = 0; x < districtList.size(); ++x) {
                            Element districtElement = districtList.get(x);
                            System.out.println(districtElement.attribute(0).getStringValue() + " " + districtElement.attribute(1).getStringValue());
                            districtArrayList.add(new District(districtElement.attribute(0).getStringValue(), districtElement.attribute(1).getStringValue()));
                        }
                        cityArrayList.add(new City(cityElement.attribute(0).getStringValue(), districtArrayList));
                    }
                    provinceArrayList.add(new Province(list.get(i).attribute(0).getStringValue(), cityArrayList));
                }
                System.out.println(provinceArrayList);
            } catch (Exception e) {
                System.out.println("25 :)");
                e.printStackTrace();
            }
        }
    
        /**
         * 使用SAXParser
         */
        public void parseMethod () {
            final List<Province> provinceList = new ArrayList<>();
            
            try {
                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
//                DefaultHandler
                saxParser.parse(new File("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\city.xml"), new DefaultHandler() {
                    ArrayList<City> cityArrayList;
                    ArrayList<District> districtArrayList;
        
                    @Override
                    public void startElement(String url, String localName, String qName, Attributes attributes) {
                        System.out.println(qName + " " + attributes.getValue(0));
                        if ("province".equals(qName)) {
                            cityArrayList = new ArrayList<>();
                            provinceList.add(new Province(attributes.getValue(0), cityArrayList));
                        }
                        if ("city".equals(qName)) {
                            districtArrayList = new ArrayList<>();
                            cityArrayList.add(new City(attributes.getValue(0), districtArrayList));
                        }
                        if ("district".equals(qName)) {
                            districtArrayList.add(new District(attributes.getValue(0), attributes.getValue(1)));
                        }
                    }
        
                });
                System.out.println(provinceList);
            } catch (Exception e) {
                System.out.println("90:)");
                e.printStackTrace();
            }
        }
    }

    class Province {
        private String name;
        public List<City> arrayList;
    
        public Province() {
            arrayList = new ArrayList<>();
        }
    
        public Province(String name, List<City> arrayList) {
            this.name = name;
            this.arrayList = arrayList;
        }
    
        public Province(ArrayList<City> arrayList) {
            this.arrayList = arrayList;
        }
    
        public void add (City city) {
            arrayList.add(city);
        }
        
        public void setArrayList(ArrayList<City> arrayList) {
            this.arrayList = arrayList;
        }
    
        @Override
        public String toString() {
            return "Province{" +
                    "name='" + name + '\'' +
                    ", arrayList=" + arrayList +
                    '}';
        }
    }

    class City {
        private String name;
        public List<District> arrayList;
    
        public City() {
            arrayList = new ArrayList();
        }
    
        public City(String name, List<District> arrayList) {
            this.name = name;
            this.arrayList = arrayList;
        }
    
        public City(ArrayList<District> arrayList) {
            this.arrayList = arrayList;
        }
    
        public void add (String string, String code) {
            arrayList.add(new District(string, code));
        }

        public void add (District district) {
            arrayList.add(district);
        }
    
        public List<District> getArrayList() {
            return arrayList;
        }
    
        public void setArrayList(ArrayList<District> arrayList) {
            this.arrayList = arrayList;
        }
    
        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", arrayList=" + arrayList +
                    '}';
        }
    }

    class District {
        public String name;
        public String code;
    
        public District(String name, String code) {
            this.name = name;
            this.code = code;
        }
    
        @Override
        public String toString() {
            return "District{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
    
    /**
     * 第二题
     */
    class Second {
        public void method () {
            SAXReader saxReader = new SAXReader();
            Document document = null;
            try {
                document = saxReader.read(new File("D:\\File\\Java_workspace\\20170304\\src\\javagame\\prototype\\worker.xml"));
            } catch (Exception e) {
                System.out.println("201 :)");
                System.exit(-1);
            }
            Element element = document.getRootElement();
            List<Element> workerList = element.elements();
            List<Worker> workerArrayList = new ArrayList();
            for (Element ele : workerList) {
                String id = ele.attribute(0).getStringValue();
                String name = ele.elementTextTrim("name");
                String sex = ele.elementTextTrim("sex");
                String status = ele.elementTextTrim("status");
                String address = ele.elementTextTrim("address");
                String money = ele.elementTextTrim("money");
                Worker worker = new Worker(id, name, sex, status, address, money);
                workerArrayList.add(worker);
            }
            //2
            System.out.println(workerArrayList);
            //233
        }
    
        /**
         * SAXParser
         */
        public void parserMethod () {}
    }
    
    class Worker {
        private String id;
        private String name;
        private String sex;
        private String status;
        private String address;
        private String money;
    
        public Worker() {
        }
    
        public Worker(String id, String name, String sex, String status, String address, String money) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.status = status;
            this.address = address;
            this.money = money;
        }
    
        @Override
        public String toString() {
            return "Worker{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", status='" + status + '\'' +
                    ", address='" + address + '\'' +
                    ", money='" + money + '\'' +
                    '}';
        }
    
        public String getId() {
            return id;
        }
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getStatus() {
            return status;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getMoney() {
            return money;
        }
    
        public void setMoney(String money) {
            this.money = money;
        }
    }
    
    class Third {
        public void method () {
            System.out.println("319234.matches(\"1[34578][59]//d{8}\"\t" + "319234".matches("1[34578][59]//d{8}"));
            //032-1234
            System.out.println("1234567\t" + "23213423423".matches("0\\d{2,3}-\\d{7,8}"));
        }
    }

    /**
     * 正则表达式
     */
    class Fourth {
        public void method () {
            System.out.println("aaabaaacaaa".replaceAll("aaa", "z"));
            //把字符串的"aaa","aa"或者"a" 全部替换为"*" 打印*b*c* 
            System.out.println("aaabaaca".replaceAll("a+", "*"));
            //把字符串中的数字全部替换为"z" 打印zzzazzbzzcc 
            System.out.println("123a44b35cc".replaceAll("\\d", "z"));
            
            //把字符串中的非数字全部替换为"0" 打印1234000435000 
            System.out.println("1234abc435def".replaceAll("\\D", "0"));
            
            //把字符串中的"."全部替换为"\"打印abc\def\ghi\jk 
            System.out.println("abc.def.ghi.jk".replaceAll("\\.", "\\\\"));

            //把字符串中的"a.b"全部替换为"-","a.b"表示长度为3的字符串，以a开头以b结尾 
            //打印-hello-lining 
            System.out.println("axbhelloasblining".replaceAll("a.b", "-"));

            //把字符串中的所有词字符替换为"#" 
            //正则表达式"[a-zA-Z_0-9]等价于"\w" 
            //打印#.#.#.#.#. 
            System.out.println("a.b.c.1.2.".replaceAll("\\w", "#"));
        }
        
        public void method2 () {
            String str="4566";
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(str);
            try {
                System.out.println(matcher.find());
                System.out.println(matcher.group());
            } catch (Exception e) {
                System.out.println(":) 346") ;
            }
            str="fdsfds33";
            matcher = pattern.matcher(str);
            try {
                System.out.println(matcher.find());
                System.out.println(matcher.group(0));
            } catch (Exception e) {
                System.out.println("353 :)");
            }
        }
    }
    
    class SwitchMethod {
        public void method () {
            int i = 10;
            int j = 20;
            if (i++ < j-- || i-- > j++) {
                System.out.println(i + " " + j);
            }
        }
    }

    public static void main (String[] args) {
        Day22 day22 = new Day22();

        SwitchMethod switchMethod = day22.new SwitchMethod();
        switchMethod.method();
        Math.max(3, 6);
    }
}
