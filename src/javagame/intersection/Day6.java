package javagame.intersection;

import java.util.Arrays;
import java.util.Random;

public class Day6 {
    
    public static void main (String[] args) {
        Day6 day6 = new Day6();
        /**
         * 1、建立一个人类，有吃饭、睡觉的方法，有姓名、性别、联系电话的属性。
         *    初始化若干个对象。并将每个人的信息打印输出。
         */
        Humanity[] humanity = new Humanity[3];
        char character = '三';
        String num = "1899988998";
        Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            humanity[i] = day6.new Humanity("张" + character++, "男", num + random.nextInt(9));
            System.out.println(humanity[i]);
        }
        System.out.println("\n---------------------------------分割线--------------------------------------------\n");
        /**
         * 2、创建学生类，包括学生的姓名、性别、年龄、所在系、系主任，
         *    以及显示学生信息的方法showInfo。编写测试类，
         *    在测试类中创建3个学生对象，调用学生的showInfo方法。
         */
        Student[] student = new Student[5];
        for (int i = 0; i < 5; ++i) {
            student[i] = day6.new Student("李" + character++, (random.nextBoolean() ? "男" : "女"), 18 + random.nextInt(4), "数计", "黄在翔");
            System.out.println(student[i].showInfo());
        }
        System.out.println("\n---------------------------------分割线--------------------------------------------\n");
        /**
         * 3 定义管理员类
         *   属性：用户名、密码
         *   方法:show()显示管理员信息
         *   创建两个管理员类对象,输出他们的相关信息
         */
        Administrator[] administrator = new Administrator[2];
        for (int i = 0; i < 2; ++i) {
            administrator[i] = day6.new Administrator("王" + character++, String.valueOf(random.nextInt(1000000)));
            System.out.println(administrator[i]);
        }
        System.out.println("\n---------------------------------分割线--------------------------------------------\n");
        /**
         * 4、//创建一个类，为该类定义两个同名方法，分别执行下列操作
         *    //1）、传递两个整数值并找出其中较大的一个值
         *    //2）、传递三个double值并求出其乘积
         */
        Day6.Fourth fourth = day6.new Fourth();
        int x = 6, y = 9, z = 2;
        System.out.println(x + "，" + y + "中最大的是：" + fourth.strangeMethod(x, y));
        System.out.println(x + ", " + y + " 和 " + z + " 的乘积是：" + fourth.strangeMethod(x, y, z));
        System.out.println("\n---------------------------------分割线--------------------------------------------\n");
        /**
         * 5.编写客户类
         *   属性:积分、卡类型
         *   方法:show()实现客户信息
         */
        Client client = day6.new Client(random.nextInt(3010), "金卡");
        client.show();
        System.out.println("\n---------------------------------分割线--------------------------------------------\n");
        /**
         * 6.在上题基础上实现积分回馈功能：
         *   金卡客户积分大于1000份或普卡客户积分大于5000，获得回馈积分500
         *   创建客户对象(金卡会员,积分3050),输出他得到的回馈积分.
         */
        Day6.Sixth sixth = day6.new Sixth(3050, "金卡");
        sixth.feedback();
        System.out.println("他得到的回馈积分: " + sixth.getPoints());
        System.out.println("\n---------------------------------分割线--------------------------------------------\n");
        /**
         * 7、创建一个类，这个类中保存5个学生的信息，（学生的信息包括：学号，姓名，成绩）
         *    该类中有求这五个学生的平均成绩，最高分数，最低分数的方法。  
         *    编写完成后，编写测试类测试该类的三个方法。
         */
        Day6.Seventh seventh = day6.new Seventh(student);
        for (int i = 0; i < student.length; i++) {
            student[i].setScore(10 * random.nextDouble() + 72);
            System.out.println(student[i].getScore());
        }
        System.out.println("平均分：" + seventh.averageScore());
        System.out.println("最高分：" + seventh.highestScore());
        System.out.println("最低分：" + seventh.lowestScore());
    }
    
    /**
     * 1、建立一个人类，有吃饭、睡觉的方法，有姓名、性别、联系电话的属性。
     *    初始化若干个对象。并将每个人的信息打印输出。
     */
    class Humanity {
        
        private String name;
        private String gender;
        private String contact;
        
        public String toString () {
            return "姓名：" + name + ",\t性别：" + gender + ",\t联系电话：" + contact;
        }
        
        public Humanity () {
        }
        
        public Humanity (String name, String gender, String contact) {
            set(name, gender, contact);
        }
        
        private void set (String name, String gender, String contact) {
            this.name = name;
            this.gender = gender;
            this.contact = contact;
        }
        
        public void eat () {
        }
        
        public void goToBed () {
        }
    }
    
    /**
     * 2、创建学生类，包括学生的姓名、性别、年龄、所在系、系主任，
     *    以及显示学生信息的方法showInfo。编写测试类，
     *    在测试类中创建3个学生对象，调用学生的showInfo方法。
     */
    class Test {
        public void testMethod () {
            Student[] student = new Student[3];
            for (int i = 0; i < student.length; ++i) {
                student[i].showInfo();
            }
        }
        
        public void testSeventh () {
        }
    }
     
    class Student {
         
        private String name;
        private String gender;
        private int age;
        private String department;
        private String head;
        private double score;
        private String studentId;
        
        public Student (String name, String gender, int age, String department, String head) {
            set(name, gender, age, department, head, 0, "");
        }
        
        public void set (String name, String gender, int age, String department, String head, double score, String studentId) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.department = department;
            this.head = head;
            setScore(score);
            this.studentId = studentId;
        }
        
        public void setScore (double score) {
            this.score = score;
        }
        public double getScore () {
            return score;
        }
        
        public String toString () {
            return "姓名：" + name + "\t性别：" + gender + "\t年龄：" + age + "\t所在系：" + department + "\t系主任：" + head;
        }
        
        public String showInfo () {
            return toString();
        }
        
        
    }
    
    /**
     * 3 定义管理员类
     *   属性：用户名、密码
     *   方法:show()显示管理员信息
     *   创建两个管理员类对象,输出他们的相关信息
     */
    class Administrator {
        
        private String username;
        private String password;
        
        public Administrator () {
        }

        public Administrator (String username, String password) {
            set(username, password);
        }
        
        public void set (String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String toString () {
            return "用户名：" + username + "\t密码：" + password;
        }
         
        public String show () {
            return toString();
        }
        
    }

    /**
     * 4、//创建一个类，为该类定义两个同名方法，分别执行下列操作
     *    //1）、传递两个整数值并找出其中较大的一个值
     *    //2）、传递三个double值并求出其乘积
     */
    class Fourth {
        
        public int strangeMethod (int x, int y) {
            return x > y ? x : y;
        }
        
        public double strangeMethod (double x, double y, double z) {
            return x * y * z;
        }
        
    }

    /**
     * 5.编写客户类
     *   属性:积分、卡类型
     *   方法:show()实现客户信息
     */
    class Client {
        
        protected int points;
        protected String cardType;
        
        public Client (int points, String cardType) {
            set(points, cardType);
        }
        
        protected void set (int points, String cardType) {
            this.points = points;
            this.cardType = cardType;
        }
        
        public String toString () {
            return "积分：" + points + "\t卡类型：" + cardType;
        }
        
        public void show () {
            System.out.println(toString());
        }
    }

    /**
     * 6.在上题基础上实现积分回馈功能：
     *   金卡客户积分大于1000份或普卡客户积分大于5000，获得回馈积分500
     *   创建客户对象(金卡会员,积分3050),输出他得到的回馈积分.
     */
    class Sixth extends Client{
         
        Sixth (int points, String cardType) {
            super(points, cardType);
        }
        
        public void feedback () {
            if ((points > 1000 && "金卡".equals(cardType)) || (points > 5000 && "普卡".equals(cardType))) {
                points += 500;
            }
        }
        public int getPoints () {
            return points;
        }
    }
    
    /**
     * 7、创建一个类，这个类中保存5个学生的信息，（学生的信息包括：学号，姓名，成绩）
     *    该类中有求这五个学生的平均成绩，最高分数，最低分数的方法。  
     *    编写完成后，编写测试类测试该类的三个方法。
     */
    class Seventh {
        
        private Student[] students;
        private double sum = 0.0;
        private double highestDouble = 0;
        private double lowestDouble = 0;

        public Seventh (Student[] stud) {
            students = stud;
        }
        
        public double averageScore () {
            for (Student student : students) {
                sum += student.score;
            }
            return sum / students.length;
        }
        
        public double highestScore () {
            for (Student stu : students) {
                if (highestDouble < stu.score) {
                    highestDouble = stu.score;
                }
            }
            return highestDouble;
        }
        
        public double lowestScore () {
            lowestDouble = students[0].score;
            for (Student stu : students) {
                if (lowestDouble > stu.score) { 
                    lowestDouble = stu.score;
                }
            }
            return lowestDouble;
        }
    }

}
