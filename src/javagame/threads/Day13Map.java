package javagame.threads;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

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
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            //2
            System.out.println(map);
            //233
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
 
    /**
     *  3.统计一个指定的字符串中每个字符出现的个数。把结果存入到一个Map中。(key 为某个字符，value为这个字符出现的次数)。
     */
 
    /**
     * 4.(List练习)写一个可以给斗地主三家随机发牌的程序。
     * a：牌可以随机发给三个玩家
     * b：在控制台打印每个玩家的牌。
     * c：对每个玩家手中的牌按照大写排序。
     * 牌的花色："♠", "♥", "♣", "♦"
     * 思路：创建一个容器存储所有的牌，再创建三个容器分别表示三个用户的牌，三个用户轮流从第一个容器中取牌，剩下三张为底牌。把大王和小王也算进去。可以封装一个类表示牌
     */
    
    public static void main (String[] args) {
        Day13Map day13Map = new Day13Map();
        
        First first = day13Map.new First();
        first.method();
        
        Second second = day13Map.new Second();
        second.method();
        
        Third third = day13Map.new Third();
        third.method();
        
    }
    
}
