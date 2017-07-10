package javagame.intersection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 程淼 on 2017/7/8.
 */
public class Saturday2 extends JFrame implements Runnable {
    /**
     *   完成学生管理练习，1.（注册方法）要求学生可以注册系统。 2.（登录方法）如果已经注册则可以登录
     *   3.（修改信息）如果学生已经登录成功则可以修改信息。  4.显示所有信息方法
     *   Student类：
     *   属性：name,age,sex,password,phone,address
     *   构造方法初始化：
     *   注册方法：给name和password赋值（使用Scanner进行赋值），如果赋值成功返回值为true，否则返回值为false。
 
     *   登录方法：（使用Scanner输入用户名和密码）判断是否注册，如果没有注册则要求先注册再登录系统。
     *   如果已经注册则，显示欢迎进入系统。
     *   修改方法：如果没有登录则不能进行修改（提示：请登录后再修改，显示登录界面），只有登录后才可以进行学生信息的修改（显示修改成功，显示修改后的用户信息）
     *   显示所有信息的方法。
     */
    private volatile boolean runningBoolean;
    private Thread thread;
    private Scanner scanner;
    private volatile int integer;
    private ArrayList<Student> arrayList;
    private Student stu;
    
    public static void main (String[] args) {
        
        final Saturday2 saturday = new Saturday2();
        
        saturday.thread = new Thread(saturday);
        saturday.thread.start();
    
    }
    
    public void closing () {
        runningBoolean = false;
        System.exit(0);
    }
    
    public void run () {
        runningBoolean = true;
        scanner = new Scanner(System.in);
        arrayList = new ArrayList();
        System.out.println("欢迎进入XX学生管理练习");
        while (runningBoolean) {
            System.out.println("1.注册学生信息");
            System.out.println("2.登录");
            System.out.println("3.修改学生信息");
            System.out.println("4.显示学生信息");
            System.out.println("5.退出");
            processInput();
            sleep(10L);
        }
    }
    
    public void sleep (long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }
    
    public void processInput () {
    
        try {
            integer = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(e);
        }
        switch (integer) {
            case 1:
                stu = new Student();
                arrayList.add(stu);
                try {
                    System.out.println("enter name:");
                    stu.setName(scanner.next());
                    System.out.println("enter id");
                    stu.setId(scanner.next());
                    System.out.println("enter gender");
                    stu.setGender(scanner.next());
                    System.out.println("enter age");
                    stu.setAge(scanner.nextInt());
                    System.out.println("enter college");
                    stu.setCollege(scanner.next());
                } catch (Exception e) {System.out.println(e);}
                break;
            case 2:
                System.out.println("输入学生id");
                String enterString = scanner.next();
                for (Student student: arrayList) {
                    if (student.id.equalsIgnoreCase(enterString)) {
                        System.out.println("登陆成功");
                    }
                }
                break;
            case 3:
                System.out.println(Arrays.toString(arrayList.toArray()));
                System.out.println("输入要修改的学生id");
                break;
            case 4:
                System.out.println(Arrays.toString(arrayList.toArray()));
                break;
            case 5:
                closing();
                break;
            default:
                System.out.println("输入有误（1，2，3，4）");
        }
    
    }
    
    /**
     Student{
     学号
     姓名
     性别
     年龄
     系别
     年纪
     }
     */
    class Student {
    
        private String name;
        private String id;
        private String gender;
        private int age;
        private String college;
        
        public Student(){}
    
        public Student(String name, String id, String gender, int age, String college) {
            this.name = name;
            this.id = id;
            this.gender = gender;
            this.age = age;
            this.college = college;
        }
    
        public String getName() {
        
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getId() {
            return id;
        }
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getGender() {
            return gender;
        }
    
        public void setGender(String gender) {
            this.gender = gender;
        }
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public String getCollege() {
            return college;
        }
        
        public void setCollege(String college) {
            this.college = college;
        }
    
        @Override
        public String toString() {
            return "Student{" + "name='" + name + '\'' + ", id='" + id + '\'' + ", gender='" + gender + '\'' + ", age=" + age +
                    ", college='" + college + '\'' +
                    '}';
        }
    
    }
     
    /**
     欢迎进入XX学生管理练习
     1.注册学生信息
     2.登录
     3.修改学生信息
     4.显示学生信息
 
 
     请选择：
     1.--->注册的功能
 
     2.--->欢迎登录：请输入姓名，密码
 
 
     3.--->修改信息：
     A：
 
 
     4.--->显示信息：
     printInfo()
     */
    public void printfInfo () {
        System.out.println("欢迎进入XX学生管理练习");
        System.out.println("1.注册学生信息");
        System.out.println("2.登录");
        System.out.println("3.修改学生信息");
        System.out.println("4.显示学生信息");
        System.out.println("5.退出");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    static class ConnectionFactor {
        public static Connection getConnection () {
            Connection connection = null;
            try {
                connection =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3360/qq", "user1", "37934bit");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
    
}
