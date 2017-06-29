package javagame.intersection;

import java.util.Scanner;

public class Day2_1 {
    public static void main (String[] args) {
//        First first = new First();
//        first.method();
//        Second second = new Second();
//        second.method();
//        Third third = new Third();
//        third.method();
//        Fourth fourth = new Fourth();
//        fourth.method();
//        Fifth fifth = new Fifth();
//        fifth.method();
//        Sixth sixth = new Sixth();
//        sixth.method();
//        Seventh seventh = new Seventh();
//        seventh.method();
//        Eighth eighth = new Eighth();
//        eighth.method();
//        Ninth ninth = new Ninth();
//        ninth.method();
//        Tenth tenth = new Tenth();
//        tenth.method();
//        Eleventh eleventh = new Eleventh();
//        eleventh.method();
        Fourteenth fourteenth = new Fourteenth();
        fourteenth.method();
    }

    /**
     * 1.试着编写一个程序，判断一个整数n（100<n<1000）的奇偶性， 如果为偶数，则输出大写字母“A”否则输出“B”。
     */
    static class First {
        public void method () {
            System.out.println("请输入一个整数(大于100，小于1000 )：");
            Scanner scanner = new Scanner(System.in);
            int integer = scanner.nextInt();
            scanner.close();
            if (integer > 100 && integer < 1000) {
                System.out.println(integer % 2 == 0 ? "A" : "B");
            }
        }
    }

    /**
     * 2.给定一个年份，判断是否是闰年。满足A或者B中的任意一条，就是闰年。
     *   A：能被4整除，并且不能被100整除。
     *   B：或者能被400整除。
     */
    static class Second {
        public void method () {
            System.out.println("请输入年份：");
            Scanner scanner = new Scanner(System.in);
            int yearInt = scanner.nextInt();
            scanner.close();
            boolean leapYearBoolean = false;
            if ((yearInt % 4 == 0 && yearInt % 100 != 0) || yearInt % 400 == 0) {
                leapYearBoolean = true;
            } else {
                leapYearBoolean = false;
            }
            System.out.println(yearInt + (leapYearBoolean ? "是" : "不是") + "闰年");
        }
    }

    /**
     * 3.给定月份，判断该月有多少天。
     */
    static class Third {
        public void method () {
            int[] monthArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入一个月份：");
            int monthInt = scanner.nextInt();
            scanner.close();
            if (monthInt > 0 && monthInt < 13 ) {
                System.out.println(monthInt + "月有" + monthArray[monthInt - 1] + "天");
            }
        }
    }

    /**
     * 4.实现登录，正确的用户名是administer 密码是123
     *   如果输入的正确的，则显示欢迎administer，否则显示用户名或密码错误
     */
    static class Fourth {
        public void method () {
            String userName;
            String password;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入用户名：");
            userName = scanner.next();
            System.out.println("请输入密码：");
            password = scanner.next();
            scanner.close();
            if ("admin".equalsIgnoreCase(userName) && "123".equalsIgnoreCase(password)) {
                System.out.println("欢迎admin");
            } else {
                System.out.println("用户名或密码错误");
            }
        }
    }

    /**
     * 5.输入年龄和性别信息，如果年龄超过7岁 或 年龄超过5岁而且性别为男，
     *   则显示"能搬动桌子"，否则显示"不能搬动桌子"
     */
    static class Fifth {
        public void method () {
            int ageInt;
            String genderString;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入年龄：");
            ageInt = scanner.nextInt();
            System.out.println("请输入性别(男/女)：");
            genderString = scanner.next();
            scanner.close();
            if (ageInt > 7 || ageInt > 5 && "男".equals(genderString)) {
                System.out.println("能搬动桌子");
            } else {
                System.out.println("不能搬动桌子");
            }
        }
    }

    /**
     * 6.输入三个整数，如果第一个数是最大的，则把它与第三个数进行交换，否则把它与第二个数进行交换
     */
    static class Sixth {
        public void method () {
            int[] intArray = new int[3];
            System.out.println("请输入三个整数：");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < intArray.length; ++i) {
                intArray[i] = scanner.nextInt();
            }
            scanner.close();
            if (intArray[0] > intArray[1] && intArray[0] > intArray[2]) {
                intArray[0] = intArray[0] + intArray[2];
                intArray[2] = intArray[0] - intArray[2];
                intArray[0] = intArray[0] - intArray[2];
            } else {
                intArray[1] = intArray[0] + intArray[1];
                intArray[0] = intArray[1] - intArray[0];
                intArray[1] = intArray[1] - intArray[0];
            }
            System.out.println("如果第一个数是最大的，则把它与第三个数进行交换，否则把它与第二个数进行交换");
            for (int i = 0; i < intArray.length; ++i) {
                System.out.print(intArray[i] + "\t");
            }
        }
    }

    /**
     * 7.输入考试成绩
     *   如果是100分，则输出"他爸爸给她买辆车"
     *   如果是大于等于90分，则输出"他妈妈则给她买台笔记本电脑"
     *   如果是大于等于60分，则输出"他妈妈给她买部手机"
     *   否则输出"没有礼物"
     */
    static class Seventh {
        public void method () {
            Scanner scanner = new Scanner(System.in);
            double scoreDouble;
            System.out.println("请输入考试成绩：");
            scoreDouble = scanner.nextDouble();
            scanner.close();
            if (scoreDouble == 100) {
                System.out.println("他爸爸给她买辆车");
            } else if (scoreDouble >= 90) {
                System.out.println("他妈妈给她买台笔记本电脑");
            } else if (scoreDouble >= 60) {
                System.out.println("他妈妈给她买部手机");
            } else {
                System.out.println("没有礼物");
            }
        }
    }

    /**
     * 8.输入机票的原价、出行的月份、机票类型（头等舱为1,经济舱为2），
     *   如果在4月到10月为旺季，头等舱显示机票价格打9折，经济舱打7.5折
     *   如果是淡季，头等舱显示机票价格打6折，经济舱打3折
     */
    static class Eighth {
        public void method () {
            int monthInt;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入出行的月份：");
            monthInt = scanner.nextInt();
            scanner.close();
            if (monthInt > 4 && monthInt < 10) {
                System.out.println("头等舱显示机票价格打9折，经济舱打7.5折");
            } else {
                System.out.println("头等舱显示机票价格打6折，经济舱打3折");
            }
        }
    }

    /**
     * 9.输入星期几，
     *   如果是1、3、5，则显示"学习编程"
     *   如果是2、4、6，则显示"学习英语"
     *   如果是周日，则显示"休息"
     *   否则显示"输入不正确"
     */
    static class Ninth {
        public void method () {
            int day = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入星期几（1/2/3/4/5/6/7）：");
            day = scanner.nextInt();
            scanner.close();
            if (day == 1 || day == 3 || day == 5) {
                System.out.println("学习编程");
            } else if (day == 2 || day == 4 || day == 6) {
                System.out.println("学习英语");
            } else if (day == 7) {
                System.out.println("休息");
            } else {
                System.out.println("输入不正确");
            }
        }
    }

    /**
     * 10.输入两个操作数，根据设定的运算符，计算出对应的结果
     */
    static class Tenth {
        public void method () {
            int x, y;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入两个操作数：");
            x = scanner.nextInt();
            y = scanner.nextInt();
            scanner.close();
            System.out.println("+ : " + (x + y));
            System.out.println("- : " + (x - y));
            System.out.println("* : " + (x * y));
            System.out.println("/ : " + (x / y));
        }
    }

    /**
     * 11.编写一个程序，可以输入人的年龄，如果该同志的年龄大于 18 岁，
     *   则输出“你年龄大于 18， 要对自己的行业负责，送入监狱” 。
     *   如果该同志的年龄大于 10 岁并且小于等于 18，
     *   则输出 “你的年龄也不小了，把你送少管所” 。
     *   其它情况，则输出“小孩子，下次注意”
     */
    static class Eleventh {
        public void method () {
            int ageInt;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入人的年龄：");
            ageInt = scanner.nextInt();
            scanner.close();
            if (ageInt > 18) {
                System.out.println("你年龄大于 18， 要对自己的行业负责，送入监狱"); 
            } else if (ageInt > 10) {
                System.out.println("你的年龄也不小了，把你送少管所");
            } else {
                System.out.println("小孩子，下次注意");
            }
        }
    }

    /**
     * 14 编写一个成绩测评系统，输入语文、英语、数学、
     * 化学和物理五门课程成绩。输出对学生的评级，要求平均分超过85，
     * 无不及格成绩，为优秀，有不及格成绩为良好，70以上为良好，60以上为及格，其余为不及格。
     */
    static class Fourteenth {
        public void method () {
            Scanner scanner = new Scanner(System.in);
            double[] scoreArray = new double[5];
            double averageDouble = 0;
            double sum = 0;
            String[] mess = {"语文", "英语", "数学", "化学", "物理"};
            for (int i = 0; i < scoreArray.length; ++i) {
                System.out.println("请输入" + mess[i] + "的成绩：");
                scoreArray[i] = scanner.nextDouble();
            }
            scanner.close();
            String ratingString = "";
            for (int i =0; i < scoreArray.length; ++i) {
                sum += scoreArray[i];
            }
            averageDouble = sum / scoreArray.length;
            //2
            for (int i = 0; i < 5; ++i) {
                System.out.println(scoreArray[i]);
            }
            System.out.println(averageDouble);
            //233
            if (averageDouble > 70 && averageDouble <= 85) {
                ratingString = "良好";
            } else if (averageDouble > 60) {
                ratingString = "及格";
            }
            for (int i = 0; i < scoreArray.length; ++i) {
                if (averageDouble > 85 && scoreArray[i] > 60) {
                    ratingString = "优秀";
                } else {
                    ratingString = "良好";
                    break;
                }
            }
            System.out.println(ratingString);
        }
    }
}