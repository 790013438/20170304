package javagame.intersection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.Arrays;

public class Day9 {
    
    public static void main (String[]args) {
        
        String personData = "Doe, John 5/15/65";
        String[] tokens = personData.split("[,\\s/]+");
        System.out.println(Arrays.toString(tokens) + "\t" + tokens.length);
        
        String line = "Doe, John 5/15/65";
        String[] words = line.split("[^\\p{L}\\p{N}]+");
        System.out.println(Arrays.toString(words));

        Day9 day9 = new Day9();
        /**
         * 1、定义一个QQ，
         * a：需要属性nickName,id,password,age 
         * b: 分别提供无参和有参构造方法，有参构造方法可以对属性可以进行初始化。
         * c：给每个属性提供相应的setter和getter方法
         * d：提供方法一个聊天的方法，可以输出与谁在聊天。chat(QQ qq)
         * e: 写一个测试类，并测试上述属性和方法。
         */
        QQ qq = day9.new QQ();
        System.out.println("\n\t与谁在聊天");
        qq.chat(day9.new QQ("\tqwerty"));
        SwingUtilities.invokeLater(new Runnable () {
            public void run () {
                System.out.println(JOptionPane.showConfirmDialog(null, "qwerty"));
            }
        });
        /**
         * 2、创建一个学生类Student
         * a: 为其添加三个属性 id name sex
         * b: 添加两个构造方法分别初始化id name sex 和 name sex并加入空的构造方法
         * c: 编写一个方法show()使用this关键字显示出学生的所有信息
         * d: 为学生类加入一个静态的属性address
         * e: 创建两个学生对象，分别为address赋值。看其变化
         * f: 根据显示情况画出其内存图
         */
        Student[] student = new Student[2];
        student[0] = new Student();
        student[1] = new Student();
        Student.address = "qwerty";
        System.out.println(Student.address);
        Student.address = "abcdef";
        System.out.println(Student.address);
        /**
         * 3、编写一个MyTime类
         * 要求MyTime类是一个单例模式，可以提供如下操作：
         * 在程序中，经常要对时间进行操作，但是并没有时间类型的数据。那么，我们可以自己实现一个时间类，来满足程序中的需要。        
         * 定义名为MyTime的类，其中应有三个整型成员：时（hour），分（minute），秒（second），
         * 为了保证数据的安全性，这三个成员变量应声明为私有。
         * 为MyTime类定义构造方法，以方便创建对象时初始化成员变量。 
         * 再定义diaplay方法，用于将时间信息打印出来。
         * 为MyTime类添加以下方法：
         * addSecond(int sec)
         * addMinute(int min)
         * addHour(int hou)
         * subSecond(int sec)
         * subMinute(int min)
         * subHour(int hou)
         * 分别对时、分、秒进行加减运算。
         */
        MyTime myTime = MyTime.getInstance(23, 58, 50);
        for (int i = 0; i < 10; ++i) {
            myTime.addMinute(1);
            System.out.println(myTime);
        }

        /**
         * 4、编写一个计数器类
         * 编写一个计数器类，其中包含属性count用来表示计数器的当前值。
         * 方法increment()表示计数器加1
         * 方法decrement()表示计数器减1
         * 方法reset()用来清零
         */
        Count count = day9.new Count();
        count.increment();
        System.out.println(count);
        
        /**
         * 5、定义一个网络用户类
         * 定义一个网络用户类，要处理的信息有用户ID、用户密码、email地址。
         * 在建立类的实例时，把以上三个信息都作为构造函数的参数输入，
         * 其中用户ID和用户密码是必须的，缺省的email地址是用户ID加上字符串"@mail.yztcedu.com"
         */
        Client client = day9.new Client();
        System.out.println(client.toString());
        
        /**
         * 6、static练习
         * 创建一个名称为StaticDemo的类，并声明一个静态变量和一个普通变量。
         * 对变量分别赋予10和5的初始值。在main()方法中输出变量值。
         */
        StaticDemo staticDemo = new StaticDemo();
        staticDemo.setFirstInt(10);
        staticDemo.setSecondInt(5);
        System.out.println(staticDemo);
        
        /**
         * 7、继承练习
         * 有农民(farmer),教师(teacher),科学家(scientist),服务生(attendant) 
         * a: 其中农民,服务生只有基本工资.  
         * b: 教师除基本工资外,还有课酬(元/天)  
         * c: 科学家除基本工资外,还有年终奖 
         * d: 请你写出相关类,将各种类型的员工的全年工资打印出来 
         */
        Farmer farmer = day9.new Farmer();
        System.out.println(farmer);
        Teacher teacher = day9.new Teacher();
        System.out.println(teacher);
        Scientist scientist = day9.new Scientist();
        System.out.println(scientist);
        Attendant attendant = day9.new Attendant();
        System.out.println(attendant);
        
        /**
         * 8、
         * 创建两个具有继承结构的两个类，父类和子类均有自己的静态代码块、构造代码块、构造方法。      
         * 创建多个子类对象，观察他们的运行顺序
         */
        SuperClass superClass = new SuperClass();
        SubClass subClass = new SubClass();
    }
    
    /**
     * 1、定义一个QQ，
     * a：需要属性nickName,id,password,age 
     * b: 分别提供无参和有参构造方法，有参构造方法可以对属性可以进行初始化。
     * c：给每个属性提供相应的setter和getter方法
     * d：提供方法一个聊天的方法，可以输出与谁在聊天。chat(QQ qq)
     * e: 写一个测试类，并测试上述属性和方法。
     */
    class QQ {
        
        String nickName;
        String id;
        String password;
        int age;
        
        public QQ () {}
        
        public QQ (String name) {
            this.nickName = name;
        }
        
        public QQ (String nickName, String id, String password, int age) {
            this.nickName = nickName;
            this.id = id;
            this.password = password;
            this.age = age;
        }
        
        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
        
        public void chat(QQ qq) {
            System.out.println(qq);
        }
        
        @Override
        public String toString () {
            return nickName;
        }

    }

    /**
     * 2、创建一个学生类Student
     * a: 为其添加三个属性 id name sex
     * b: 添加两个构造方法分别初始化id name sex 和 name sex并加入空的构造方法
     * c: 编写一个方法show()使用this关键字显示出学生的所有信息
     * d: 为学生类加入一个静态的属性address
     * e: 创建两个学生对象，分别为address赋值。看其变化
     * f: 根据显示情况画出其内存图
     */
    static class Student {

        public String id;
        public String name;
        public String sex;
        public static String address;

        public Student () {} 

        public Student (String id, String name, String sex) {
            this.id = id;
            this.name = name;
            this.sex = sex;
        }

        public Student (String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        public String show () {
            return toString();
        }

        @Override
        public String toString () {
            return id + "\t" + name + "\t" + sex;
        }

    }

    /**
     * 3、编写一个MyTime类
     * 要求MyTime类是一个单例模式，可以提供如下操作：
     * 在程序中，经常要对时间进行操作，但是并没有时间类型的数据。那么，我们可以自己实现一个时间类，来满足程序中的需要。        
     * 定义名为MyTime的类，其中应有三个整型成员：时（hour），分（minute），秒（second），
     * 为了保证数据的安全性，这三个成员变量应声明为私有。
     * 为MyTime类定义构造方法，以方便创建对象时初始化成员变量。 
     * 再定义diaplay方法，用于将时间信息打印出来。
     * 为MyTime类添加以下方法：
     * addSecond(int sec)
     * addMinute(int min)
     * addHour(int hou)
     * subSecond(int sec)
     * subMinute(int min)
     * subHour(int hou)
     * 分别对时、分、秒进行加减运算。
     */
    static class MyTime {

        private int hour;
        private int minute;
        private int second;
        private static MyTime myTime = null;

        private MyTime () {}

        private MyTime (int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public static MyTime getInstance (int hour, int minute, int second) {
            if (myTime == null) {
                myTime = new MyTime(hour, minute, second);
            }
            return myTime;
        }
    
        @Override
        public String toString() {
            return "MyTime{hour = " + hour + ", minute = " + minute + ", second = " + second + '}';
        }
    
        public void addSecond (int sec) {
            second += sec;
            minute += second / 60;
            hour += minute / 60;
            minute %= 60;
            second %= 60;
        }

        public void addMinute (int min) {
            minute += min;
            hour += minute / 60;
            minute %= 60;
        }

        public void addHour (int hou) {
            hour += hou;
        }

        public void subSecond (int sec) {
            second -= sec;
        }

        public void subMinute (int min) {
            minute -= min;
        }

        public void subHour (int hou) {
            hour -= hou;
        }

    }

    /**
     * 4、编写一个计数器类
     * 编写一个计数器类，其中包含属性count用来表示计数器的当前值。
     * 方法increment()表示计数器加1
     * 方法decrement()表示计数器减1
     * 方法reset()用来清零
     */
    class Count {

        private int count;

        public void increment () {
            count++;
        }

        public void decrement () {
            count--;
        }

        public void reset () {
            count = 0;
        }
    
        @Override
        public String toString() {
            return "Count{" + "count = " + count + "}";
        }
    }

    /**
     * 5、定义一个网络用户类
     * 定义一个网络用户类，要处理的信息有用户ID、用户密码、email地址。
     * 在建立类的实例时，把以上三个信息都作为构造函数的参数输入，
     * 其中用户ID和用户密码是必须的，缺省的email地址是用户ID加上字符串"@mail.yztcedu.com"
     */
    class Client {
        private String id;
        private String password;
        private String address = "@mail.yztedu.com";
    
        public Client() {
        }
    
        @Override
        public String toString() {
            return "Client{" + "id='" + id + '\'' + ", password='" + password + '\'' + ", address='" + address + '\'' + '}';
        }
    
        public Client(String id, String password, String address) {
            this.id = id;
            this.password = password;
            this.address = address;
        }
    
    }

    /**
     * 6、static练习
     * 创建一个名称为StaticDemo的类，并声明一个静态变量和一个普通变量。
     * 对变量分别赋予10和5的初始值。在main()方法中输出变量值。
     */
    static class StaticDemo {
        private static int firstInt;
        private int secondInt;
    
        public int getFirstInt() {
            return firstInt;
        }
    
        public void setFirstInt(int firstInt) {
            this.firstInt = firstInt;
        }
    
        public int getSecondInt() {
            return secondInt;
        }
    
        @Override
        public String toString() {
            return "StaticDemo{" + "static firstInt:" + firstInt + ", secondInt=" + secondInt + '}';
        }
    
        public void setSecondInt(int secondInt) {
            this.secondInt = secondInt;
        }
    
    }

    /**
     * 7、继承练习
     * 有农民(farmer),教师(teacher),科学家(scientist),服务生(attendant) 
     * a: 其中农民,服务生只有基本工资.  
     * b: 教师除基本工资外,还有课酬(元/天)  
     * c: 科学家除基本工资外,还有年终奖 
     * d: 请你写出相关类,将各种类型的员工的全年工资打印出来 
     */
    class Wage {
    
        double basic;
        
        @Override
        public String toString() {
            return "Wage{" + "basic=" + basic + '}';
        }
    
    }
    
    class Farmer extends Wage {
        @Override
        public String toString() { return "Farmer{" + "basic=" + basic + '}';
        }
    }
    
    class Attendant extends Wage {
        @Override
        public String toString() { return "Attendant{" + "basic=" + basic + '}';
        }
    }
    
    class Teacher extends Wage {
    
        double Remuneration;
        
        @Override
        public String toString() { return "Teacher{" + "basic=" + basic + ", Remuneration=" + Remuneration + '}';
        }
    
    }
    
    class Scientist extends Wage {
        
        double awards;
        
        @Override
        public String toString() {
            return "Scientist{" + "basic=" + basic + ", awards=" + awards + '}';
        }
    
    }
    

    /**
     * 8、
     * 创建两个具有继承结构的两个类，父类和子类均有自己的静态代码块、构造代码块、构造方法。
     * 创建多个子类对象，观察他们的运行顺序
     */
    static class SuperClass {

        static {
            System.out.println("SuperClass static");
        }
        
        {
            System.out.println(toString());
        }
        
        @Override
        public String toString() {
            return "SuperClass{}";
        }
    
    }
    
    static class SubClass {

        static {
            System.out.println("SubClass static");
        }
        
        {
            System.out.println(toString());
        }
    
        @Override
        public String toString() {
            return "SubClass{}";
        }
        
    }
    
}
