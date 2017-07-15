package javagame.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by 79001 on 2017/7/14.
 */
public class Day13Map {
    
    /**
     * 1.创建Map集合，用于存储人的信息。存储3个人的信息（HashMap实现)，
     * 并遍历该集合
     * 要求通过Map.Entry来完成输出key和value信息
     */
    class First {

        public void method () {
            Map<String, Person> hashMap = new HashMap<>();
            Person[] persons = new Person[3];
            persons[0] = new Person("qwerty", 12);
            persons[1] = new Person("Doe", 34);
            persons[2] = new Person("John", 56);
            for (int i = 0; i < persons.length; ++i) {
                hashMap.put(persons[i].getName(), persons[i]);
            }
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
    
    class Person {
        
        String name;
        int age;
    
        public Person() {
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Person person = (Person) o;
        
            if (age != person.age) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }
    
        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }
    /**
     * 2.创建Map集合，用于存储人的信息。存储3个人的信息（Hashtable实现)，
     * 并遍历该集合
     * 要求通过Map.Entry来完成输出key和value信息
     */
    class Second {
        
        public void method () {

            Map<String, Person> hashtable = new Hashtable<>();
            Person[] persons = new Person[3];
            persons[0] = new Person("qwerty", 12);
            persons[1] = new Person("Doe", 34);
            persons[2] = new Person("John", 56);
            for (int i = 0; i < persons.length; ++i) {
                hashtable.put(persons[i].getName(), persons[i]);
            }
            Iterator iterator = hashtable.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            
        }
    }

    /**
     *  3. (Map练习)已知某学校的教学课程内容安排如下：
     *  完成下列要求：
     *  1） 使用一个Map，以老师的名字作为键，以老师教授的课程名作为值，表示上述 课程安排。
     *  2） 增加了一位新老师Allen 教JDBC
     *  3） Lucy 改为教CoreJava
     *  4） 遍历Map，使用两种方式输出所有的老师及老师教授的课程
     */
    class Third {

        public void method () {
            Map<String, Course> map = new HashMap();
            Course[] courses = new Course[7];
            courses[0] = new Course("Tom", "CoreJava");
            courses[1] = new Course("John", "Oracle");
            courses[2] = new Course("Susan", "Oracle");
            courses[3] = new Course("Jerry", "JDBC");
            courses[4] = new Course("Jim", "Unix");
            courses[5] = new Course("Jim", "JSP");
            courses[6] = new Course("Lucy", "JSP");
            for (int i = 0; i < courses.length; ++i) {
                map.put(courses[i].getTeacher(), courses[i]);
            }
            //增加新老师
            courses[0] = new Course("Allen", "JDBC");
            map.put(courses[0].getTeacher(), courses[0]);
            //Lucy改为教CoreJava
            courses[6].setLesson("CoreJava");
            //遍历
            Iterator<Map.Entry<String, Course>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Course> entry = iterator.next();
                System.out.println(entry.getKey() + "\t" + entry.getValue());
                
            }
            for (HashMap.Entry<String, Course> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        }

    }
    
    class Course {
        
        private String teacher;
        private String lesson;
    
        public Course() {
        }
    
        public Course(String teacher, String lesson) {
            this.teacher = teacher;
            this.lesson = lesson;
        }
    
        public String getTeacher() {
            return teacher;
        }
    
        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }
    
        public String getLesson() {
            return lesson;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Course course = (Course) o;
        
            if (teacher != null ? !teacher.equals(course.teacher) : course.teacher != null) return false;
            return lesson != null ? lesson.equals(course.lesson) : course.lesson == null;
        }
    
        @Override
        public int hashCode() {
            int result = teacher != null ? teacher.hashCode() : 0;
            result = 31 * result + (lesson != null ? lesson.hashCode() : 0);
            return result;
        }
    
        @Override
        public String toString() {
            return "Course{" +
                    "teacher='" + teacher + '\'' +
                    ", lesson='" + lesson + '\'' +
                    '}';
        }
    
        public void setLesson(String lesson) {
            this.lesson = lesson;
        }
    }

    /**
     *  2.(Map练习)下图是，截止到2009年，世界杯局部年份和夺冠国家。
     *  要求：在键盘输入年份，输出夺冠国家。
     */
    class WorldCup {

        private Map<Integer, Win> treeMap = new TreeMap();
        
        public void method () {
            System.out.println("输入年份");
            Integer integer = 0;
            Scanner scanner = new Scanner(System.in);
            try {
                integer = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("夺冠国家");
            try {
                System.out.println(treeMap.get(integer).champion);
            } catch (Exception e) {
                System.out.println("没有");
            }
        }

        public void initialize () {
            Win[] win = new Win[18];
            win[0] = new Win();
            win[0].session = 1;
            win[0].year = 1930;
            for (int i = 1; i < 18; ++i) {
                win[i] = new Win();
                win[i].session = win[i - 1].session + 1;
                win[i].year = win[i - 1].year + 4;
                if (i == 3) {
                    win[3].year = 1950;
                }
            }
            win[3].champion = win[0].champion = "乌拉圭";
            win[17].champion = win[11].champion = win[2].champion = win[1].champion = "意大利";
            win[13].champion = win[9].champion = win[4].champion = "德国";
            win[16].champion = win[14].champion = win[8].champion = win[6].champion = win[5].champion = "巴西";
            win[7].champion = "英格兰";
            win[12].champion = win[10].champion = "阿根廷";
            win[15].champion = "法国";
            for (int i = 0; i < 18; ++i) {
                treeMap.put(win[i].year, win[i]);
            }
        }

    }

    class Win {
        public int session;
        public Integer year; 
        public String champion;
    
        @Override
        public String toString() {
            return "Win{" +
                    "session=" + session +
                    ", year=" + year +
                    ", champion='" + champion + '\'' +
                    "}\n";
        }
    }
 
    /**
     *  3.统计一个指定的字符串中每个字符出现的个数。把结果存入到一个Map中。(key 为某个字符，value为这个字符出现的次数)。
     */
    class Count {
        String string;
        Map<Character, Integer> treeMap = new TreeMap<>();
        
        public Count () {
        }
        
        public Count (String string) {
            this.string = string;
        }
    
        public void setString(String string) {
            this.string = string;
        }
    
        public void method () {
//            统计一个指定的字符串中每个字符出现的个数
            for (int i = 0 ; i < string.length(); ++i) {
                Character character = string.charAt(i);
                try {
                    //先获取character，如果没有跳到NullPointerException,添加数据
                    int integer = treeMap.get(character);
                    //更新
                    treeMap.put(character, integer + 1);
                } catch (NullPointerException e) {
                    treeMap.put(character, 1);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println(treeMap);
        }
    }
 
    /**
     * 4.(List练习)写一个可以给斗地主三家随机发牌的程序。
     * a：牌可以随机发给三个玩家
     * b：在控制台打印每个玩家的牌。
     * c：对每个玩家手中的牌按照大写排序。
     * 牌的花色："♠", "♥", "♣", "♦"
     * 思路：创建一个容器存储所有的牌，再创建三个容器分别表示三个用户的牌，三个用户轮流从第一个容器中取牌，剩下三张为底牌。把大王和小王也算进去。可以封装一个类表示牌
     */
    class Fourth {
        
        public void method () {
            List<String> user0 = new ArrayList<>();
            List<String> user1 = new ArrayList<>();
            List<String> user2 = new ArrayList<>();
            List<String> left = new ArrayList<>();
            
            //分发牌
            List<String> pokerList = Poker.getInstance();
            Collections.shuffle(pokerList);
            Iterator<String> iterator = pokerList.iterator();
            for (; user0.size() < 17 && iterator.hasNext();) {
                user0.add(iterator.next());
                user1.add(iterator.next());
                user2.add(iterator.next());
            }
            
            //底牌
            for (int i = 0; i < 3; ++i) {
                left.add(iterator.next());
            }
            
            //给用户手中的牌排序
            Poker.sort(user0);
            Poker.sort(user1);
            Poker.sort(user2);
            Poker.sort(left);
            
            //打印
            System.out.println(user0);
            System.out.println(user1);
            System.out.println(user2);
            System.out.println(left);
        }
        
    }
    
    static class Poker {
    
        private static String[] orderString = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "小怪", "大怪"};
        private static String order = "\"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"J\", \"Q\", \"K\", \"A\", \"2\", \"小怪\", \"大怪\"";
        private static String[] color = {"♠", "♥", "♣", "♦"};
        private List<String> arrayList = new ArrayList<>();
        
        private Poker () {
            initialize();
        }
        
        public static List getInstance () {
            return new Poker().arrayList;
        }
        
        public void initialize () {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < orderString.length - 2; ++i) {
                for (int j = 0; j < color.length; ++j) {
                    arrayList.add(stringBuilder.append(orderString[i]).append(color[j]).toString());
                    stringBuilder.delete(0, stringBuilder.length());
                }
            }
            arrayList.add(orderString[13]);
            arrayList.add(orderString[14]);
        }
        
        public static void sort (List arrayList) {
            arrayList.sort(new Comparator<String> () {
                @Override
                public int compare(String s1, String s2) {
                    if (s2.equals("大怪")) {
                        return -1;
                    }
                    if (s1.equals("大怪")) {
                        return 1;
                    }
                    if (s1.equals("小怪")) {
                        return 1;
                    }
                    if (s2.equals("小怪")) {
                        return -1;
                    }
                    return order.indexOf(s1.charAt(0)) - order.indexOf(s2.charAt(0));
                }
            });
        }
    
    }
    
    public static void main (String[] args) {
        Day13Map day13Map = new Day13Map();
        
//        First first = day13Map.new First();
//        first.method();
//
//        Second second = day13Map.new Second();
//        second.method();
//
//        Third third = day13Map.new Third();
//        third.method();
        
//        WorldCup worldCup = day13Map.new WorldCup();
//        worldCup.initialize();
//        worldCup.method();
        
        Count count = day13Map.new Count("qwertyqwerqwer");
        count.method();
        
        Fourth fourth = day13Map.new Fourth();
        fourth.method();
        
    }
    
}
