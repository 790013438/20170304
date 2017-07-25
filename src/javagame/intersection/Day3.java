package javagame.intersection;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Day3 {
    
    public static void main (String[] args) {
//        Server first = new Server();
//        first.method();
//        Second second = new Second();
//        second.method();
//        Third third = new Third();
//        third.whileMethod();
//        Fourth fourth = new Fourth();
//        fourth.method();
        Fifth fifth = new Fifth();
        fifth.method();
        Sixth sixth = new Sixth();
        sixth.method();
//        Seventh seventh = new Seventh();
//        seventh.method();
        Eighth eighth = new Eighth();
        eighth.method();
        System.out.println("_____________________________________");
        Ninth ninth = new Ninth();
        ninth.method();
        System.out.println("\n_____________________________________\n");
        Tenth tenth = new Tenth();
        tenth.method();
        System.out.println("\n_____________________________________\n");
        Eleventh eleventh = new Eleventh();
        eleventh.method();
        System.out.println("\n_____________________________________\n");
//        Twelfth twelfth = new Twelfth();
//        twelfth.method();
//        Thirteenth thirteen = new Thirteenth();
//        thirteen.method();
        Fourteenth fourteenth = new Fourteenth();
        fourteenth.method();
        fourteenth.secondMethod();
//        Fifteenth fifteenth = new Fifteenth();
//        fifteenth.method();
        /**
         * 分桃
         */
        Division division = new Division();
        division.method();
    }
    
    /**
     * 1.循环五次录入周一至周五每日学习时间,计算出平均学习时间
     */
    static class First {
        public void method () {
            double[] studyTime = new double[5];
            double sum = 0;
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < 5; ++i) {
                System.out.println("请输入周 " + (i + 1) + " 学习时间：");
                studyTime[i] = scanner.nextDouble();
                sum += studyTime[i];
            }
            scanner.close();
            System.out.println("平均学习时间：" + (sum / studyTime.length));
        }
    }
    
    /**
     * 2.统计1到100之间既是3的倍数又是5的倍数的个数
     */
    static class Second {
        public void method () {
            int multiplie = 0;
            int threeInt = 3;
            int count = 0;
            while (multiplie <= 100) {
                multiplie = 5 * threeInt;
                count++;
                threeInt += 3;
            }
            System.out.println("1到100之间既是3的倍数又是5的倍数的个数有" + (count - 1) + "个");
        }
    }
    
    /**
     * 3.打印1——100的所有自然数，（使用3种语法）。
     */
    static class Third {
        public void whileMethod () {
            int i = 0;
            while (i < 100) {
                i++;
                System.out.print(i + ", ");
            }
            System.out.println();
            i = 0;
            do {
                System.out.print(++i + ", ");
            } while (i < 100);
            System.out.println();
            for (i = 0; i < 100; ++i) {
                System.out.print((i + 1) + ", ");
            }
            System.out.println();
        }
    }

    /**
     * 4.打印1——100的所有数，除了7的倍数的数。（使用3种语法）
     */
    static class Fourth {
        public void method () {
            int i = 1;
            do {
                if (i % 7 != 0) {
                    System.out.print(i + ", ");
                }
            } while(i++ < 100);
            System.out.println();

            i = 0;
            while (i++ < 100) {
                if (i % 7 != 0) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println();
            
            for (i = 1; i < 101; i++) {
                if (i % 7 != 0) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 5.（使用3种语法）找出1000以内所有的7的倍数。
     */
    static class Fifth {
        public void method () {
            int i = 1;
            int sum = 7;
            do {
                System.out.print(sum + ", ");
            } while ((sum += 7) < 1000);
            System.out.println();

            i = 1;
            sum = 0;
            while ((sum += 7) < 1000) {
                System.out.print(sum + ", ");
            }
            System.out.println();

            sum = 0;
            for (i = 0; (sum += 7) < 1000; ++i) {
                System.out.print(sum + ", ");
            }
            System.out.println();
        }
    }

    /**
     * 6.求1+2+3+…+100的和
     */
    static class Sixth {
        public void method () {
            System.out.println(49 * 100 + 100 + 50);
        }
    }

    /**
     * 7.求5!
     */
    static class Seventh {
        public void method () {
            int sum = 1;
            for (int i = 0; i < 5; ++i) {
                sum *= (i + 1);
            }
            System.out.println("5！ = " + sum);
        }
    }

    /**
     * 8.输出20-80之间能够被3整除的数，每5个输出一行。
     */
    static class Eighth {
        public void method () {
            int i = 21;
            int count = 0;
            while (i < 80) {
                System.out.print(i + "\t");
                i += 3;
                count++;
                if (count % 5 == 0) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * 9. 打印1900年--2200年之间所有的闰年，每4个输出一行。
     */
    static class Ninth {
        public void method () {
            int leapYear = 1900;
            int count = 0;
            while (leapYear < 2200) {
                leapYear += 4;
                if ((leapYear % 4 == 0 && leapYear % 100 != 0) || leapYear % 400 == 0) {
                    System.out.print(leapYear + "\t");
                    count++;
                }
                if (count % 5 == 0) {
                    System.out.println();
                }
            }
        }
    }
    
    /**
     * 10.输出所有三位数中的水仙花数（使用3种语法）
     *    三位整数，每一位上的立方和等于这个数
     *    135 = 1^3 +3^3 + 5^3
     */
    static class Tenth {
        public void method () {
            int i = 100;
            while (++i < 999) {
                if (i == Math.pow(i % 10, 3) + Math.pow(i / 10 % 10, 3) + Math.pow(i / 100, 3)) {
                    System.out.println(i);
                }
            }
        }
    }

    /**
     * 11.  1+2-3+4-5.......(打印1....100之间的和)
     */
    static class Eleventh {
        public void method () {
            int sum = 1;
            int multiple = 1;
            for (int i = 2; i < 101; i++) {
                sum += i * multiple;
                multiple = -multiple;
            }
            System.out.println("sum = " + sum);
        }
    }

    /**
     * 12.编写一个程序，最多接收10个数，求这些数的和。用户可以通过输入999终止程序，并显示输入的数的和
     */
    static class Twelfth {
        public void method () {
            double[] doubleArray = new double[10];
            double sum = 0;
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < 10; ++i) {
                System.out.println("请输入第 " + (i + 1) + "个数：");
                doubleArray[i] = scanner.nextDouble();
                if (doubleArray[i] == 999) {
                    break;
                }
                sum += doubleArray[i];
            }
            scanner.close();
            System.out.println("sum = " + sum);
        }
    }
    
    /**
     * 13编写程序，从键盘输入6名学生的5门成绩，分别统计出每个学生的平均成绩
     */
    static class Thirteenth {
        public void method () {
            SwingUtilities.invokeLater(new Runnable() {
                public void run () {
                    double[][] scoreArray = new double[6][5];
                    double sum = 0;
                    double averageScore = 0;
                    for (int i = 0; i < 6; ++i) {
                        sum = 0;
                        for (int j = 0; j < 5; ++j) {
                            String enterString = JOptionPane.showInputDialog("请输入" + (i + 1) + "学生，第" + (j + 1) + "个学生的成绩：");
                            if (enterString != null) {
                                try {
                                    scoreArray[i][j] = Double.parseDouble(enterString);
                                } catch(Exception e) {}
                            }
                            sum += scoreArray[i][j];
                        }
                        averageScore = sum / (scoreArray[0].length);
                        JOptionPane.showMessageDialog(null, "平均成绩：" + averageScore);
                        System.out.println("平均分" + averageScore);
                    }
                }
            });
        }
    }
    
    /**
     * 14编写一个程序，求出满足下列条件的四位数：该数是个完全平方数，
     * 且第一、三位数字之和为10，第二、四位数字之积为12。
     */
    static class Fourteenth {
        public void method () {
            int firstInt;
            int[][] secondInt = {{2, 6}, {3, 4}, {4, 3}, {6, 2}};
            for (firstInt = 1; firstInt < 10; ++firstInt) {
                int sum = 0;
                sum = firstInt * 1000 + (10 - firstInt) * 10;
                for (int i = 0; i < 4; ++i) {
                    sum += secondInt[i][0] * 100 + secondInt[i][1];
                    if (Math.sqrt(sum) == (int)(Math.sqrt(sum))) {
                        System.out.print(sum + "\t");
                    }
                    sum -= secondInt[i][0] * 100 + secondInt[i][1];
                }
            }
            System.out.println("\n");
        }
        
        public void secondMethod () {
            int temp;
            int n;
            for (temp = 32; temp <= 99; temp++) {
                n = temp * temp;
                if ((n % 10) * (n / 100 % 10) == 12) {
                    if ((n / 10 % 10) + (n / 1000) == 10) {
                        System.out.println(n);
                    }
                }
            }
        }
    }

    /**
     * 15编写一个程序，求满足如下条件的最大的n：
     */
    static class Fifteenth {
        volatile boolean runningBoolean = true;
        public void method () {
            int sum = 0;
            int n = 31;
            //2
            int i;
            //233
            while (runningBoolean) {
                sum = 0;
                for (i = n; i >= 0; --i) {
                    sum += i * i;
                    //假设n为31，如果加起来大于1000，排除，最小的n减一
                    if (sum > 1000) {
                        n--;
                        break;
                    }
                    System.out.println(i + " == " + 1);
                    if (i == 1) {
                        runningBoolean = false;
                    }
                }
            }
            System.out.println(n);
            JOptionPane.showMessageDialog(null, n);
        }
    }

    /**
     * 3 猴子分桃：海 滩上有一堆桃子，五只猴子来分。
     * 第一只猴子把这堆桃子凭据分为五份，多了一个， 这只猴子把多的一个扔入海中，拿走了一份。
     * 第二只猴子把剩下的桃子又平均分成五份，又多了一个， 
     * 它同样把多的一个扔入海中，拿走了一份，第三、第四、第五只猴子都是这样做的，
     * 问海滩上原来最少有多少个桃子？
     * @author 79001
     *
     */
    static class Division {
        public double reverse (int n, int left) {
            if (n == 1) {
                return left;
            } else {
                return reverse(n - 1, left) * 1.25 + 1;
            }
        }
        
        public void method () {
            for (int i = 1; i < 1000; ++i) {
                boolean clearBoolean = false;
                for (int count = 1; count < 6; count++) {
                    clearBoolean = false;
                    if (reverse(count, i * 5 + 1) == (int)reverse(count, i * 5 + 1)) {
                        clearBoolean = true;
                    } else {
                        clearBoolean = false;
                        break;
                    }
                }
                if (clearBoolean) {
                    JOptionPane.showMessageDialog(null, "总数："+ reverse(5, i * 5 + 1) + "\n最后1只猴子把这堆桃子分为5份，他看到的桃子总数：" + (i * 5 + 1) + "\n拿走了一份，最后剩的桃子：" + (i * 4));
                }
                System.out.println(reverse(5, i));
            }
        }
    }
    
}
