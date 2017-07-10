package javagame.intersection;

import java.util.Arrays;
import java.util.Formatter;

public class Day8 {
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static void main (String[] args) {
        
        //2
        String personData = "Doe, John 5/15/65";
        String[] tokens = personData.split("[, /]");
        System.out.println(String.valueOf(tokens[1]) + ", "  + ("".equals(tokens[1])));
        System.out.println("\t\tlength");
        System.out.println(Arrays.toString(tokens) + "\t" + tokens.length);
        Formatter formatterOut = new Formatter(System.out);
        formatterOut.format("%2d%10.2f%n", 2, Math.sqrt(2));
        String p = null;
        String myName = p = "Elliot Koffman";
        p.equals("");
        myName = myName.substring(7) + ", " + myName.substring(0, 6);
        System.out.println(myName.toString());
//        myName.charAt(0) = 'x';
//        myName[0] = 'x';
        String keyboard = "qewrty";
        System.out.println("\tqwerty");
        System.out.println(keyboard.charAt(0));
        System.out.println(keyboard.length());
        System.out.println(keyboard.indexOf('o'));
        System.out.println(keyboard.indexOf('y'));
        String upper = keyboard.toUpperCase();
        System.out.println(upper);
        System.out.println(keyboard);
        System.out.println(keyboard.charAt(keyboard.length() - 1));
        System.out.println(keyboard.substring(0, keyboard.length() - 1));
        System.out.println("\n\tnature log");
        for (int i = 0; i < 20; ++i) {
            System.out.println(Math.pow(2, i) * 1000 + "\t" + Math.log(Math.pow(2, i) * 1000));
        }
        System.out.println("\n\tpowers of 2");
        for (int i = 0; i < 10; ++i) {
            System.out.println(i + "\t" + i * i);
        }
        //233
        //2
//        Random random = new Random();
//        random.nextInt(10);
//        for (double i = 0.1; i < 20; i += 0.1) {
//            System.out.println(i + " --> \t" +Math.rint(i));
//        }
        System.out.println("\n\tsquare roots");
        for (int i = 0; i < 10; ++i) {
            System.out.println(i + "\t" + Math.sqrt(i));
        }
        System.out.println(StrictMath.PI);
        //233
        Day8 day8 = new Day8();
        /**
         * 1、创建一个Vehicle类并将它声明为抽象类。
         * 在Vehicle类中声明一个NoOfWheels方法，使它返回一个字符串值。
         * 创建两个类Car和Motorbike从Vehicle类继承，
         * 并在这两个类中实现NoOfWheels方法。
         * 在Car类中，应当显示“四轮车”信息；而在Motorbike类中，应当显示“双轮车”信息。
         * 创建另一个带main方法的类，在该类中创建Car和Motorbike的实例，并在控制台中显示消息。
         * 创建一个名称为Vehicle的接口，在接口中添加两个带有一个参数的方法start()和stop()。
         * 在两个名称分别为Bike和Bus的类中实现Vehicle接口。
         * 创建一个名称为interfaceDemo的类，在interfaceDemo的main()方法中创建Bike和Bus对象，
         * 并访问start()和stop()方法。
         */
        Car car = day8.new Car();
        System.out.println(car.noOfWheels());
        Motorbike motorbike = day8.new Motorbike();
        System.out.println(motorbike.noOfWheels());

        Bike bike = day8.new Bike();
        bike.start("自行车");
        bike.stop("自行车");
        Bus bus = day8.new Bus();
        bus.start("公交车");
        bus.stop("公交车");
        System.out.println("------------------------------分割线-------------------------------------");
        /**
         * 2、（1）定义一个抽象类Shape，内部有一个抽象方法：public double area(); 
         * （2）分别定义两个类Circle, Rectangle，继承Shape，并覆盖其内部的抽象方法；
         */  
        System.out.println("------------------------------分割线-------------------------------------");
        /**
         * 3、建立一个Java接口IDrink，应当：  
         * a、声明一个抽象方法taste()，该方法负责输出饮料的味道； 
         * 建立Drink的实现类类：  
         * a、分别建立Drink的子类：Coffee（代表咖啡），Beer（代表啤酒），Milk（代表牛奶）;  
         * b、实现taste()方法，要求在控制台打印各自的味道特征。
         * 建立一个Taster类
         * a、创建一个方法，可以品尝不同的饮料，并说出他们的味道
         */
        Taster taster = day8.new Taster();
        taster.sample();
    }

    /**
     * 1、创建一个Vehicle类并将它声明为抽象类。
     * 在Vehicle类中声明一个NoOfWheels方法，使它返回一个字符串值。
     * 创建两个类Car和Motorbike从Vehicle类继承，
     * 并在这两个类中实现NoOfWheels方法。
     * 在Car类中，应当显示“四轮车”信息；而在Motorbike类中，
     * 应当显示“双轮车”信息。
     * 创建另一个带main方法的类，在该类中创建Car和Motorbike的实例，
     * 并在控制台中显示消息。
     * 创建一个名称为Vehicle的接口，在接口中添加两个带有一个参数的方法start()和stop()。
     * 在两个名称分别为Bike和Bus的类中实现Vehicle接口。
     * 创建一个名称为interfaceDemo的类，在interfaceDemo的main()方法中创建Bike和Bus对象，
     * 并访问start()和stop()方法。
     */
    abstract class Vehicle {
        
        public String noOfWheels () {
            return "";
        }
        
    }

    class Car extends Vehicle {

        @Override
        public String noOfWheels () {
            return "四轮车";
        }

    }

    class Motorbike extends Vehicle {

        @Override
        public String noOfWheels () {
            return "双轮车";
        }

    }

    interface VehicleInterface {

        public void start (String name); 

        public void stop(String name);

    }

    class Bike implements VehicleInterface {

        @Override
        public void start (String name) {
            System.out.println("启动");
        }

        @Override
        public void stop (String name) {
            System.out.println("停止");
        }

    }

    class Bus implements VehicleInterface {

        @Override
        public void start (String name) {
            System.out.println("启动");
        }

        @Override
        public void stop (String name) {
            System.out.println("停止");
        }

    }

    /**
     * 2、（1）定义一个抽象类Shape，内部有一个抽象方法：public double area(); 
     * （2）分别定义两个类Circle, Rectangle，继承Shape，并覆盖其内部的抽象方法；
     */  
    abstract class Shape {

        public abstract double area ();

    }

    class Circle extends Shape {

        @Override
        public double area () {
            return 0.0;
        }

    }

    class Rectangle extends Shape {

        @Override
        public double area () {
            return 0.0;
        }

    }

    /**
     * 3、建立一个Java接口IDrink，应当：  
     * a、声明一个抽象方法taste()，该方法负责输出饮料的味道； 
     * 建立Drink的实现类类：  
     * a、分别建立Drink的子类：Coffee（代表咖啡），Beer（代表啤酒），Milk（代表牛奶）;  
     * b、实现taste()方法，要求在控制台打印各自的味道特征。
     * 建立一个Taster类
     * a、创建一个方法，可以品尝不同的饮料，并说出他们的味道
     */
    interface Drink {
        void taste ();
    }

    class Coffee implements Drink {

        @Override
        public void taste () {
            System.out.println("咖啡");
        }

    } 

    class Beer implements Drink {

        @Override
        public void taste () {
            System.out.println("啤酒");
        }

    }

    class Milk implements Drink {

        @Override
        public void taste () {
            System.out.println("牛奶");
        }

    }
    
    class Taster {

        public Drink drink = null;

        public void sample () {
            Day8 day8 = new Day8();
            drink = day8.new Coffee();
            drink.taste();
            drink = day8.new Beer();
            drink.taste();
            drink = day8.new Milk();
            drink.taste();
        }
    }
}
