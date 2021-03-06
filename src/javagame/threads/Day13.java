package javagame.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by 79001 on 2017/7/13.
 */
public class Day13 {
    
    <T> T get(T obj) {
        return obj;
    }
    
    class Eighth<E> {
        
        public E get (E o) {
            return o;
        }
        
        public E plus (E o) {
            return o;
        }
    }
    
    class Ninth {
        public void method () {
            String string = "Doe, John 5/15/65";
            String[] tokens = string.split("[\\p{Z},/]+");
            System.out.println(Arrays.toString(tokens));
            System.out.println(string.indexOf(", "));
            System.out.println(string.indexOf("/"));
        }
    }
    
    public static void main (String[] args) {
        Day13 day13 = new Day13();
        char ch = 'a';
        System.out.println(new Character(ch).toString());
        System.out.println("" + ch);
        System.out.println(new StringBuilder().append(ch).toString());
//        individual tokens
        Ninth ninth = day13.new Ninth();
        ninth.method();
        
        //What is stored in result
        StringBuilder result = new StringBuilder();
        String sentence = "Let's all learn how to program in Java";
        String[] tokens = sentence.split("\\s+");
        for (String token : tokens) {
            result.append(token).append("\n");
        }
        System.out.println(result);
        
//        rewrite
        StringJoiner stringJoin = new StringJoiner(", ");
        String[] array = "Elliot Koffman".split("\\p{Z}");
        for (int i = array.length - 1; i >= 0; --i) {
            stringJoin.add(array[i]);
        }
        System.out.println(stringJoin);
        
        //rewrite
        StringBuilder myName = new StringBuilder("Elliot Koffman");
        StringBuilder myNameFirstLast = myName;
        myName = new StringBuilder(myName.substring(7)).append(", ").append(myName.substring(0, 6));
        System.out.println(myName);
        
//        extract the words in the string "Nancy* has thirty-three*** fine!! teeth."
        String string = "Nancy* has thirty-three*** fine!! teeth.";
        System.out.println(Arrays.toString(string.split("[\\p{P}\\p{Z}]+")));
        
//        Evaluate each of these expressions.
        System.out.println("happy".equals("Happy"));
        System.out.println("happy".compareTo("Happy"));
        System.out.println("happy".equalsIgnoreCase("Happy"));
        System.out.println("happy".equals("happy".charAt(0) + "Happy".substring(1)));
        System.out.println("happy" == "happy".charAt(0) + "Happy".substring(1));
        

        CharSequence character = new String();
//        泛型
        System.out.println(day13.get("qwerty"));
        Eighth<String> eighth = day13.new Eighth();
        eighth.get("qwerty");
        
//        Collections
        System.out.println(Collections.EMPTY_LIST);
        
        List<Student> arrayList = new ArrayList();
        arrayList.add(day13.new Student("qwerty", 192));
        arrayList.add(day13.new Student("Dick", 35));
        arrayList.add(day13.new Student("Tom", 23));
        arrayList.add(day13.new Student("om", 23));
        arrayList.add(day13.new Student("m", 23));
        arrayList.add(day13.new Student("cm", 23));
        System.out.println(Collections.binarySearch(arrayList, day13.new Student("qwerty", 12), new Comparator<Student>() {
            @Override
            public int compare (Student o1, Student o2) {
                return o1.age - o2.age;
            }
        }));
        Collections.sort(arrayList);
        System.out.println(arrayList);
    
        String[] names = {"Tom", "Dick", "Harry"};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(names[0]);
        for (int i = 1 ; i < names.length; ++i) {
            stringBuilder.append(", ");
            stringBuilder.append(names[i]);
        }
        String results = stringBuilder.toString();
        System.out.println(result);
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < names.length; ++i) {
            stringJoiner.add(names[i]);
        }
        results = stringJoiner.toString();
        System.out.println(result);
        System.out.println("%n");
        
        System.out.println(stringBuilder.capacity());
        stringBuilder.append("day me");
        stringBuilder.insert(9, "to ");
        stringBuilder.insert(5, " birth");
        stringBuilder.replace(18, 20, "you");
        
        HashMap hashMap = new HashMap();
        Map map = hashMap;
        map.put("qwerty", 1234);
        map.put("John", 3456);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    
    /**
     * 1.创建Set集合，用于存储Student对象。尝试存储相同对象。看能否成功，并遍历该集合（容器）。
     */
    class First {
        public void method () {
            Set<Student> hashSet = new HashSet();
            hashSet.add(new Student("qwerty", 23));
            hashSet.add(new Student("John", 34));
            System.out.println(hashSet);
        }
    }
    
    class Student implements Comparable{
        private String name;
        private int age;
    
        public Student() {
        }
    
        public Student(String name, int age) {
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
    
        @Override
        public int compareTo(Object o) {
            if (o.getClass() == getClass()) {
                Student other = (Student)o;
                return age - other.getAge();
            }
            return 0;
        }
    }
    
    /**
     * 2.创建Set集合，用于存储Student对象。尝试存储相同对象数值，要求重写equals和hashCode。
     * 看能否成功，并遍历该集合（容器）。
     */
    class Second {
        public void method () {
            Set<Student> hashSet = new HashSet();
            hashSet.add(new Student("qwerty", 23));
            hashSet.add(new Student("John", 34));
            System.out.println(hashSet);
        }
    }
    /**
     * 3.创建3个Person类（姓名、年龄属性）对象，存入ArrayList集合中。并通过3种遍历方式，访问遍历该容器（集合）。
     * (迭代器，增强for，普通for)
     */
    class Third {
        public void method () {
            List<Person> arrayList = new ArrayList();
            //第一种
            for (Person p : arrayList) {
                System.out.println(p);
            }
            //第二种
            for (int i = 0; i < arrayList.size(); ++i) {
                System.out.println(arrayList.get(i));
            }
            //第三种
            Iterator iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
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
    }
    /**
     * 4.HashSet集合练习：
     * （1）创建一个Employee类，添加属性:name,age，salary; 封装这些属性并分别设置各个属性的get和set方法。
     * （2）在Employee 类基础上，为Employee 类添加相应的代码，使得Employee 对象能正确放入HashSet 中：
     * 如果年龄和姓名相同就认为他们为同一个对象。并编写相应的测试代码。
     * 例如：雇员的基本信息如下：
     * 姓名	年龄	工资
     * Tom	18	1500
     * Jack	18	1500
     * Mike	18	1600
     * Marry	17	2000
     */
    class Fouth {
        public void method () {
            Set<Employee> hashSet = new HashSet();
            hashSet.add(new Employee("qwerty", 12, 34));
            hashSet.add(new Employee("John", 23, 34));
            hashSet.add(new Employee("Tom", 34, 45));
        }
    }
    class Employee {
        private String name;
        private int age;
        private double salary;
    
        public Employee() {
        }
    
        public Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
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
    
        public double getSalary() {
            return salary;
        }
    
        public void setSalary(double salary) {
            this.salary = salary;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Employee employee = (Employee) o;
        
            if (age != employee.age) return false;
            if (Double.compare(employee.salary, salary) != 0) return false;
            return name != null ? name.equals(employee.name) : employee.name == null;
        }
    
        @Override
        public int hashCode() {
            int result;
            long temp;
            result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            temp = Double.doubleToLongBits(salary);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }
}
