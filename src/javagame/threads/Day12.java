package javagame.threads;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by 79001 on 2017/7/12.
 */
public class Day12 {
    /**
     * 作业：将jdk8新特性文档中的所有代码，运行一遍
     */
    /**
     * 集合练习1
     * （1）创建一个Person类，添加属性:name,age; 封装这些属性并分别设置各个属性的方法。
     * （2）根据用户输入的对象个数创建Person对象，接收用户在控制台上输入的每个对象的信息。(注意，将有些Person对象的名字和年龄设置相同)
     * （4）使用迭代器迭代输出该Collecion集合
     * （3）创建一个Collection集合的对象，将Person对象添加到Collection集合中。
     * (5) 删除重复的对象。如果姓名和年龄都相同则认为对象重复了
     */
    class First {
        public void method () {
            Collection<Person> arrayList = new ArrayList();
            int count = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入学生个数：");
            try {
                count = scanner.nextInt();
            } catch (Exception e) {}
            for (int i = 0; i < count; ++i) {
                Person person = new Person();
                System.out.println("输入学生姓名：");
                try {
                    String enterString = scanner.next();
                    person.setName(enterString);
                } catch (Exception e) {}
                System.out.println("输入学生年龄：");
                try {
                    int enterInt = scanner.nextInt();
                    person.setAge(enterInt);
                } catch (Exception e) {}
                arrayList.add(person);
            }
            Object[] array = arrayList.toArray();
            for (int i = 0; i < array.length; ++i) {
                Person temp = (Person)array[i];
                for (int j = i + 1; j < array.length; ++j) {
                    if (temp.equals(array[j])) {
                        arrayList.remove(temp);
                    }
                }
            }
            Iterator<Person> iteractor = arrayList.iterator();
            while (iteractor.hasNext()) {
                Person person = iteractor.next();
                System.out.println(person);
            }
        }
    }
    
    class Person {

        private String name;
        private int age;
    
        public Person() {
        }
    
        public Person(String name, int age) {
        
            this.name = name;
            this.age = age;
        }
    
        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age='" + age + '\'' + '}';
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Person person = (Person) o;
        
            if (age != person.age) return false;
            return name.equals(person.name);
        }
    
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
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
    }
    
    /**
     * List集合练习2
     * a：自定义类型User,包含2个属性 name和age
     * b：把多个User对象存储到List集合中。
     * c：把List集合中的元素按照年龄从小到大排列，如果年龄相等，按照姓名的字母顺序降序排列。
     * d：删除List集合中重复的元素。年龄和姓名相同就认为他们相同。
     */
    class Third {

        private List<User> arrayList = new ArrayList();

        public void method () {
            arrayList.add(new User("John", 123));
            arrayList.add(new User("qwerty", 12));
            arrayList.add(new User("Joe", 13));
            arrayList.sort(new Comparator<User>() {
                
                @Override
                public int compare(User o1, User o2) {
                    if (o1.getAge() != o2.getAge())
                        return o1.getAge() - o2.getAge();
                    return o1.getName().compareTo(o2.getName());
                }
                
            });
            //删除重复的
            Set set = new TreeSet();
            set.addAll(arrayList);
            arrayList.containsAll(set);
        }

    }

    class User {
        private String name;
        private int age;
    
        public User() {
        }
    
        public User(String name, int age) {
            this.name = name;
            this.age = age;
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
    
        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            User user = (User) o;
        
            if (age != user.age) return false;
            return name != null ? name.equals(user.name) : user.name == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }
    }
 
    /**
     * List集合练习3
     * 使用List存储企鹅信息  Collection ArrayList
     * 把多个企鹅的信息添加到集合中
     * 查看企鹅的数量及所有企鹅的信息
     * 删除集合中部分企鹅的元素
     * 判断集合中是否包含指定企鹅
     */
    class Second {
        public void method () {
            Collection arrayList = new ArrayList();
            System.out.println("企鹅数量" + arrayList.size());
            arrayList.contains(new Penguin("qwerty", 12));
            List subArrayList = new ArrayList();
            subArrayList.add(new Penguin("qwerty", 123));
            arrayList.removeAll(subArrayList);
        }
    }
    
    class Penguin {
        private String name;
        private int age;
    
        public Penguin() {
        }
        
        public Penguin(String name, int age) {
            this.name = name;
            this.age = age;
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
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Penguin penguin = (Penguin) o;
        
            if (age != penguin.age) return false;
            return name.equals(penguin.name);
        }
    
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }
    
        @Override
        public String toString() {
            return "Penguin{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }
    
    /**
     * 集合练习4
     * 根据图片结果显示结果，设计类
     */
    class Fourth {
    
        public void method () {
            List<Student> arrayList = new ArrayList();
            arrayList.add(new Student("张三", 21));
            arrayList.add(new Student("李四", 22));
            arrayList.add(new Student("王五", 23));
            arrayList.add(new Student("赵六", 24));
            
            Day12.School tsinghua = new School("清华大学");
            tsinghua.setArrayList(arrayList.subList(0, arrayList.size()));
            School Beijing = new School("北京大学");
            Beijing.setArrayList(arrayList.subList(0, arrayList.size()));
        }
    }
    
    class School {
        private String name;
        private List<Student> arrayList = new ArrayList();
    
        public School() {
        }
    
        public School(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public List<Student> getArrayList() {
            return arrayList;
        }
    
        public void setArrayList(List<Student> arrayList) {
            this.arrayList = arrayList;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            School school = (School) o;
    
            return name != null ? name.equals(school.name) : school.name == null;
        }
    
        @Override
        public String toString() {
            return "School{" +
                    "name='" + name + '\'' + arrayList +
                    '}';
        }
    
        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
    
    class Student {
    
        private String name;
        private int age;
    
        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Student student = (Student) o;
        
            if (age != student.age) return false;
            return name != null ? name.equals(student.name) : student.name == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }
    
        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    /**
     * 集合练习5
     * 1). 声明 Student 类
     * 属性： 	name：姓名
     * age： 年龄
     * math：数学成绩
     * html: html成绩
     * sql：sql成绩
     *
     * 生成每个属性的set/get方法
     *
     * 2). 实例化五个学生对象，分别添加相应数据
     *
     * 3). ArrayList应用
     *
     * 步骤一：将5个学生信息封装到ArrayList
     * 步骤二：将ArrayList中的学生信息输出
     * 步骤三：将学生的年龄加一岁后输出
     * 步骤四：求SQL科目的平均成绩
     *
     * 步骤五：求每个人的总成绩
     *
     * 注：每一步骤分别实现
     */
    class Fifth {
        
        public void method () {
            Calculate calculate = new Calculate();
            calculate.enter(new Student1("qwerty", 12, 32f, 34f, 23f));
            calculate.enter(new Student1("John", 13, 32f, 23f, 23f));
            calculate.enter(new Student1("Joe", 34, 23f, 98f, 25f));
            calculate.enter(new Student1("Tom", 21, 26f, 44f, 23f));
            calculate.enter(new Student1("Keyboard", 20, 12f, 23f, 23f));
            System.out.println(calculate);
            //将学生年龄加1， sql的平均成绩， 每个人的总成绩
            float sum = 0;
            for (Student1 student : calculate.getArrayList()) {
                student.plusAge(1);
                sum += student.getSql();
                System.out.println("个人总成绩");
                System.out.println(student.sum());
            }
            System.out.println("sql平均成绩：");
            System.out.println(sum / calculate.getArrayList().size());
            
        }
        
    }
    
    static class Calculate {
        private List<Student1> arrayList = new ArrayList();
    
        public List<Student1> getArrayList() {
            return arrayList;
        }
    
        public void setArrayList(List arrayList) {
            this.arrayList = arrayList;
        }
    
        //输入学生信息
        public  void enter (Student1 student) {
            arrayList.add(student);
        }
    
        //将学生信息输出
        @Override
        public String toString() {
            return "Calculate{" +
                    "arrayList=" + arrayList +
                    '}';
        }
        
    }
    class Student1 {
        private String name;
        private int age;
        private float math;
        private float html;
        private float sql;
        private float sum;
    
        public Student1() {
        }
    
        public Student1(String name, int age, float math, float html, float sql) {
            this.name = name;
            this.age = age;
            this.math = math;
            this.html = html;
            this.sql = sql;
        }
    
        //将学生的年龄加一岁
        public void plusAge(int age) {
            this.age += age;
        }
        
        public float sum () {
            return sum = math + html + sql;
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
    
        public float getMath() {
            return math;
        }
    
        public void setMath(float math) {
            this.math = math;
        }
    
        public float getHtml() {
            return html;
        }
    
        public void setHtml(float html) {
            this.html = html;
        }
    
        public float getSql() {
            return sql;
        }
    
        public void setSql(float sql) {
            this.sql = sql;
        }
    
        @Override
        public String toString() {
            return "Student1{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", math=" + math +
                    ", html=" + html +
                    ", sql=" + sql +
                    '}';
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Student1 student1 = (Student1) o;
        
            if (age != student1.age) return false;
            if (Float.compare(student1.math, math) != 0) return false;
            if (Float.compare(student1.html, html) != 0) return false;
            if (Float.compare(student1.sql, sql) != 0) return false;
            return name != null ? name.equals(student1.name) : student1.name == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            result = 31 * result + (math != +0.0f ? Float.floatToIntBits(math) : 0);
            result = 31 * result + (html != +0.0f ? Float.floatToIntBits(html) : 0);
            result = 31 * result + (sql != +0.0f ? Float.floatToIntBits(sql) : 0);
            return result;
        }
    }
 
    /**
     * 集合练习6
     * 设计书签类：网址  标题
     * 设计书签管理类，要求做的功能：
     * 1、建立书签，加入管理器中
     * 2、根据网址删除一个书签
     * 3、根据网址查看书签
     * 4、查看所有书签
     */
    class Management {
        private List<BookMark> arrayList = new ArrayList();
    
        public Management() {
        }
    
        public Management(List<BookMark> arrayList) {
            this.arrayList = arrayList;
        }

        public void delete (String url) {
            arrayList.remove(url);
        }
    
        public void add (BookMark bookMark) {
            arrayList.add(bookMark);
        }
        
        public String getTitle (String url) {
            if (url == null) return null;
            for (BookMark o : arrayList) {
                if (o.getUrl().equals(url)) {
                    return o.getTitle();
                }
            }
            return null;
        }
    }
    class BookMark {
        private String url;
        private String title;
    
        public BookMark() {
        }
    
        public BookMark(String url, String title) {
            this.url = url;
            this.title = title;
        }
    
        public String getUrl() {
            return url;
        }
    
        public void setUrl(String url) {
            this.url = url;
        }
    
        public String getTitle() {
            return title;
        }
    
        public void setTitle(String title) {
            this.title = title;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            BookMark bookMark = (BookMark) o;
        
            if (url != null ? !url.equals(bookMark.url) : bookMark.url != null) return false;
            return title != null ? title.equals(bookMark.title) : bookMark.title == null;
        }
    
        @Override
        public int hashCode() {
            int result = url != null ? url.hashCode() : 0;
            result = 31 * result + (title != null ? title.hashCode() : 0);
            return result;
        }
    
    }
    
    /**
     * PrintStream,
     * DateOutputStream
     */
    class Sixth {
        File file = new File("e://qwerty.txt");
    
        public void method () {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream dateInputStream = new DataInputStream(fileInputStream);
                PrintStream printStream = new PrintStream(file);
                PrintWriter printWriter = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        
        public void printRandom () {
            
            try {
                PrintStream printStream = new PrintStream(file);
                System.setOut(printStream);
                Random random = new Random();
                
                for (int i = 0; i < 100; ++i) {
                    if (i % 6 == 0 && i != 0) {
                        printStream.println();
                    }
                    printStream.print(random.nextInt(101) + 100 + ", ");
                }
                
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            
        }
    }
    

    public static void main (String[] args) {
        
        Day12 day12 = new Day12();

        /**
         * 集合练习1
         * （1）创建一个Person类，添加属性:name,age; 封装这些属性并分别设置各个属性的方法。
         * （2）根据用户输入的对象个数创建Person对象，接收用户在控制台上输入的每个对象的信息。(注意，将有些Person对象的名字和年龄设置相同)
         * （3）创建一个Collection集合的对象，将Person对象添加到Collection集合中。
         * (5) 删除重复的对象。如果姓名和年龄都相同则认为对象重复了
         */
//        First first = day12.new First();
//        first.method();
        
        Sixth sixth = day12.new Sixth();
        sixth.method();
        
    }

}
