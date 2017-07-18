package javagame.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/17.
 */
public class Day15 {
    
    /**
     * 作业1：根据编号输出课程名称
     * 需求说明：
     * 按照控制台提示输入1～3之间任一个数字，程序将输出相应的课程名称
     * 根据键盘输入进行判断。如果输入正确，输出对应课程名称。如果输入错误，给出错误提示
     * 不管输入是否正确，均输出“欢迎提出建议”语句
     */
    class First {
        public void method () {
            System.out.println("请输入1～3之间任一个数字");
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("1", "C#编程");
            hashMap.put("2", "SQL SERVER数据库");
            hashMap.put("3", ".Net ASP");
            try {
                Scanner scanner = new Scanner(System.in);
                String enterString = scanner.next();
                System.out.println(hashMap.get(enterString));
            } catch (Exception e){
                System.out.println("输入错误");
            } finally {
                System.out.println("欢迎提出建议");
            }
        }
    }
 
    /**
     * 作业2：使用throw抛出异常
     * 需求说明：
     * 在setAge(int age) 中对年龄进行判断，如果年龄介于1到100直接赋值，否则抛出异常
     * 在测试类中创建对象并调用setAge(int age)方法，使用try-catch捕获并处理异常
     */
    class Second {
        
        public void method () {
            try {
                System.out.println("请输入介于1到100的年龄");
                Scanner scanner = new Scanner(System.in);
                String enterString = scanner.next();
                Age age = new Age();
                age.setAge(Integer.parseInt(enterString));
                System.out.println("输入正确");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    class Age {
        int age;
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) throws RuntimeException{
            if (age > 1 && age < 100)
                this.age = age;
            else
                throw new RuntimeException("请输入介于1到100的年龄");
        }
    }

    /**
     * 作业3：
     * 1：除法运算功能（div(int x,int y)）
     * 2：if判断如果除数为0，throw new ArithmeticException();
     * 3：函数声明throws ArithmeticException
     * 4：main方法调用div,不进行处理
     * 5：编译通过，运行正常
     * 6：如果除数为0，报异常，程序停止。
     */
    class Third {
    
        public void method () {
            try {
                System.out.println("3 / 6 = " + div(3, 6));
                System.out.println("3 / 0 = " + div(3, 0));
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        
        public double div (int x, int y) throws ArithmeticException {
            if (y == 0)
                throw new ArithmeticException("输入了0作除数");
            return x * 1.0 / y;
        }
    
    }
    
    /**
     * 作业4：
     * 定义功能模拟登录。
     * 自定义异常:自定义描述没有IP地址的异常（NoIpException）
     * 定义一个方法， 当没有ip地址时，需要进行异常处理。当ip地址为null是需要throw new NoIpException("无法获取ip");
     */
    class Fourth {
        public void method () {
        
            error();
        
        }
        
        public void error () throws NoIpException{
            
            System.out.println("输入ip地址");
            Scanner scanner = new Scanner(System.in);
            String enterString = scanner.next();
            if (enterString.matches("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$")) {
                System.out.println("输入正确");
            } else {
                throw new NoIpException("无法获取ip");
            }
        }
    }
    
    class NoIpException extends RuntimeException {
        private String ip;
    
        public NoIpException() {
            super();
        }
    
        public NoIpException(String message) {
            super(message);
        }
    
        @Override
        public String getMessage() {
            return super.getMessage();
        }
    
        @Override
        public String toString() {
            return super.toString();
        }
    
        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }
    }
 
    /**
     * 作业5：模拟吃饭没带钱的问题
     * 1.定义吃饭功能，需要钱。（例如：eat(double money)）
     * 2.如果钱不够是不能吃放，有异常。
     * 3.自定义NoMoneyException();继承Exception 提供有参无参构造，调用父类有参构造初始化。at 方法进行判断，小于10块，throw NoMoneyException("钱不够");
     * 4.eat 方法进行声明，throws NoMoneyException
     * 5.如果钱不够老板要处理。调用者进行处理。try{}catch(){} 。
     */
    class Fifth {
        
        public void method () {
            for (double i = 6; i < 10; ++i) {
                try {
                    eat(i);
                    System.out.println(i + "够了");
                } catch (NoMoneyException e) {
                    System.out.println("Error:" + i + " " + e.getMessage());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        
        }
        
        public void eat (double money) throws NoMoneyException {
            if (((int)money & 1) == 0) {
                throw new NoMoneyException("钱需要越多越好");
            }
        }
    }
    
    class NoMoneyException extends Exception {
    
        public NoMoneyException() {
            super();
        }
    
        public NoMoneyException(String message) {
            super(message);
        }
    
        @Override
        public String getMessage() {
            return super.getMessage();
        }
    
        @Override
        public String toString() {
            return super.toString();
        }
    
        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }
    }
    
    public static void main (String[] args) {
        Day15 day15 = new Day15();
        
//        First first = day15.new First();
//        first.method();
        
//        Second second = day15.new Second();
//        second.method();
        
//        Third third = day15.new Third();
//        third.method();
        
//        Fourth fourth = day15.new Fourth();
//        fourth.method();
        
        Fifth fifth = day15.new Fifth();
        fifth.method();
    }
    
}
