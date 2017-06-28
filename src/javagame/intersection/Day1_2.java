package javagame.intersection;

import java.util.Scanner;

public class Day1_2 {
    public static void main (String[] args) {
        First first = new First();
        first.method();
        Second second = new Second();
        second.method();
        Third third = new Third();
        third.method();
        Fifth fifth = new Fifth();
        fifth.method();
        Sixth sixth =new Sixth();
        sixth.method();
        Seventh seventh = new Seventh();
        seventh.method();
        Eighth eighth = new Eighth();
        eighth.method();
        Ninth ninth = new Ninth();
        ninth.method();
        Tenth tenth = new Tenth();
        tenth.method();
    }

    //1小题
    static class First {
        public void method () {
            System.out.println(89 / 24 + "天"+ 89 % 24 + "小时");
        }
    }

    //2小题
    static class Second {
        public void method () {
            double a = 0;
            double b = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入第一个数：");
            a = scanner.nextInt();
            System.out.println("输入第二个数：");
            b = scanner.nextInt();
            System.out.println("+:" + (a + b));
            System.out.println("-:" + (a - b));
            System.out.println("*:" + a * b);
            if (b != 0) {
                System.out.println("/:" + a / b);
            }
        }
    }

    //3小题
    static class Third {
        public void method () {
            double height = 0;
            double weight = 0;
            double bmiDouble = 0;
            String resultString = "等待操作";
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入身高(m)：");
            height = scanner.nextDouble();
            System.out.println("请输入体重(kg)：");
            weight = scanner.nextDouble();
            if (height > 0) {
                bmiDouble = weight / (height * height);
            }
            if (bmiDouble < 18.5) {
                resultString = "轻体重";
            } else if(bmiDouble < 24) {
                resultString = "健康体重";
            } else if (bmiDouble < 28) {
                resultString = "超重";
            } else {
                resultString = "肥胖";
            }
            System.out.println("亲爱的 XXX，根据您输入的身高" + height + "m，体重" + weight +"kg，计算出您的BMI值为" + bmiDouble);
        }
    }

    static class Fifth {
        public void method () {
            int radius = 5;
            System.out.println("周长：" + 2 * Math.PI * radius);
            System.out.println("面积：" + Math.PI * radius * radius);
        }
    }

    static class Sixth {
        public void method () {
            int integer = 3;
            int result = (int)((integer * 10 + 5) / 2 + 3.14159);
            System.out.println(result);
        }
    }

    static class Seventh {
        public void method () {
            System.out.println(46 / 7 + "周" + 46 % 7 + "天");
        }
    }

    static class Eighth {
        public void method () {
            int x = 5;
            int y = 6;
            System.out.println("交换前：x = " + x + ", y = " + y);
            x = x + y;
            y = x - y;
            x = x - y;
            System.out.println("交换后：x = " + x + ", y = " + y);
        }
    }

    static class Ninth {
        public void method () {
            int integer = 18342;
            int temp = integer;
            int sum = 0;
            for (; temp > 0;) {
                sum += temp % 10;
                temp /= 10;
            }
            System.out.println(sum);
        }
    }

    static class Tenth {
        public void method () {
            double salaryDouble = 10000;
            System.out.println(salaryDouble * 1.05 * 1.05);
        }
    }
}
