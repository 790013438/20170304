import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by 79001 on 2017/7/28.
 */
public class ListTest {
    /**
     * 题目五： 使用List集合完成如下操作(共13分)。
     【1】创建一个Person类，添加属性:name,age; 封装这些属性并分别设置各个属性的方法。	(2分)
     【2】根据用户输入的对象个数创建Person对象，接收用户在控制台上输入的每个对象的信息。(注意，将有些Person对象的名字和年龄设置相同)(2分)
     【3】创建一个ArrayList集合，将Person对象添加到ArrayList集合中。(2分)
     【4】使用迭代器迭代输出该List集合(3分)
     【5】写一个方法，可以去除ArrayList集合中重复的Person对象。姓名和年龄相同视为重复的Person对象(4分)
     */
    static class Person implements Comparable {
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
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
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
        public int compareTo(Object o) {
            if (this == o) return 0;
            if (o == null || getClass() != o.getClass()) return -1;
    
            Person person = (Person) o;
            if (!name.equalsIgnoreCase(person.name))
                return name.compareToIgnoreCase(person.name);
            
            if (age != person.age)
                return age - person.age;
            return 0;
        }
    }
    
    public ArrayList<Person> duplicate (ArrayList<Person> arrayList) {
        TreeSet<Person> treeSet = new TreeSet();
        for (Person p : arrayList) {
            treeSet.add(p);
        }
        arrayList.clear();
        Iterator<Person> iterator = treeSet.iterator();
        while(iterator.hasNext()) {
            arrayList.add(iterator.next());
        }
        return arrayList;
    }
    
    public static void main (String[] args) {
        ArrayList<Person> arrayList = new ArrayList();
        System.out.println("请输入需要创建Person的对象个数");
        Scanner scanner = new Scanner(System.in);
        int integer = 0;
        try {
            integer = Integer.parseInt(scanner.next());
        } catch (Exception e) {}
        for (int i = 0; i < integer; ++i) {
            System.out.println("请输入第" + (i + 1) + "个人的信息");
            String name = scanner.next();
            int age = 0;
            try {
                age = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("输入整数 :)");
            }
            arrayList.add(new Person(name, age));
        }
        Iterator<Person> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();
            System.out.println(p.getName() + "\t" + p.getAge());
        }
        System.out.println("去重复元素");
        new ListTest().duplicate(arrayList);
        for (Person p : arrayList) {
            System.out.println(p);
        }
    }
}
