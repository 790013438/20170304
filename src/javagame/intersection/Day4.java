package javagame.intersection;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Day4 {
    public static void main(String[] args) {
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
        Seventh seventh = new Seventh();
        seventh.method();
        System.out.println("------------------------------");
        Eighth eighth = new Eighth();
        eighth.method();
        System.out.println("------------------------------");
//        Ninth ninth = new Ninth();
//        ninth.method();
        System.out.println("------------------------------");
        Tenth tenth = new Tenth();
        tenth.method();
    }

    /**
     * 1.编写方法，判断一个字符是否是表示数字的字符
     *   int isDIGITAL(int c);
     *   //如果是返回1，不是，返回0
     */
    static class First {
        public void method () {
            SwingUtilities.invokeLater(new Runnable() {
                    public void run () {
                        String enterString = JOptionPane.showInputDialog("请输入一字符:");
                        JOptionPane.showMessageDialog(null, " '" + enterString.charAt(0) + "' " + (isDIGITAL(enterString.charAt(0)) == 1 ? "是" : "不是") + "表示数字的字符");
                    }
            });
        }
       
        public int isDIGITAL (int c) {
            if (c <= '0' || c >= '9') {
                return 0;
            }
            return 1;
        }
    }

    /**
     * 2.编写方法，判断一个字符，是否是表示16进制数字的字符
     */
    static class Second {
        public void method () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入一个字符：");
            String enterString = scanner.nextLine();
            scanner.close();
            char enterChar = enterString.charAt(0);
            if (enterChar >= '0' && enterChar <= '9' || enterChar >= 'a' && enterChar <= 'f' || enterChar >= 'A' && enterChar <= 'F') {
                System.out.println(" '" + enterChar + "' 是表示16进制数字的字符");
            } else {
                System.out.println(" '" + enterChar + "' 不是表示16进制数字的字符");
            }
        }
    }
    
    /**
     * 3.编写方法，求4个数的平均值。
     *   int avg(int a, int b, int c, int d);
     */
    static class Third {
        public void method () {
            SwingUtilities.invokeLater(new Runnable () {
                public void run () {
                    double[] num = new double[4];
                    for (int i = 0; i < 4; ++i) {
                        String enterString = JOptionPane.showInputDialog("请输入4个数，第 " + (i + 1) + "个：");
                        try {
                            num[i] = Double.parseDouble(enterString);
                        } catch (Exception e) {}
                    }
                    JOptionPane.showMessageDialog(null, "平均值" + avg(num[0], num[1], num[2], num[3]));
                }
            });
        }
        
        public double avg (double a, double b, double c, double d) {
            return (a + b + c + d) / 4;
        }
    }

    /**
     * 4.编写方法，求一个正数的n次方
     *   int myPOW(int x, int n);
     */
    static class Fourth {
        public void method () {
            for (int i = 0; i < 5; ++i) {
                System.out.println("2的 " + i + " 次方：" + myPOW(2, i));
            }
        }
        
        public int myPOW (int x, int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return x;
            }
            for (int i = 0; i < n - 1; ++i) {
                x *= x;
            }
            return x;
        }
    }

    /**
     * 5、编写方法实现，传递一个参数，返回这个数对应的绝对值
     */
    static class Fifth {
        public void method () {
            for (int i = -3; i < 4; ++i) {
                System.out.println(i + "\t的绝对值：\t" + abs(i));
            }
        }
        public double abs (double value) {
            if (value >= 0) {
                return value;
            } else {
                return -value;
            }
        }
    }

    /**
     * 6、编写方法实现，接受一个年份，返回该年是否是闰年
     */
    static class Sixth {
        public void method () {
            for (int i = 1998; i < 2025; ++i) {
                if (leapYear(i)) {
                    System.out.println("闰年：" + i);
                };
            }
        }
        
        public boolean leapYear (int yearInt) {
            return (yearInt % 4 ==0 && yearInt % 400 != 0) || yearInt % 400 == 0;
        }
    }

    /**
     * 7、编写方法实现，接收一个三位数，判断这个数是不是一个对称数，比如757，626等都是
     */
    static class Seventh {
        public void method () {
            SwingUtilities.invokeLater (new Runnable () {
                public void run () {
                    String enterString = JOptionPane.showInputDialog("请输入一个三位数:");
                    try {
                        JOptionPane.showMessageDialog(null, assign(Integer.parseInt(enterString)) ? "这个数是一个对称数" : "这个数不是一个对称数");
                    } catch (Exception e) {}
                }
            });
        }
        public boolean assign (int integer) {
            if (integer / 100 % 10 == integer % 10) {
                System.out.println("这个" + integer + "数是一个对称数");
                return true;
            } else {
                System.out.println("这个" + integer + "数不是一个对称数");
                return false;
            }
        }
    }

    /**
     * 8、编写方法实现，计算丛1到100以内能被7整除但不是偶数的数的个数{
     */
    static class Eighth {
        public void method () {
            System.out.println("\n从1到100以内能被7整除但不是偶数的数的个数: " + count());
        }
        
        public int count () {
            int calculate = 0;
            for (int i = 0; (i += 7) < 100; ) {
                if (i % 2 != 0) {
                    System.out.print(i + "\t");
                    calculate++;
                }
            }
            return calculate;
        }
    }

    /**
     * 9、编写方法实现，将某个5位数的所有位的数值加在一起并输出
     */
    static class Ninth {
        public void method () {
            SwingUtilities.invokeLater(new Runnable () {
                public void run () {
                    String enterString = JOptionPane.showInputDialog("请输入5位整数：");
                    try {
                        JOptionPane.showMessageDialog(null, "所有位的数值加在一起：\n" + calculate(Integer.parseInt(enterString)));
                    } catch (Exception e) {}
                }
            });
        }
        
        public int calculate (int integer) {
            int sum = 0;
            while (integer != 0) {
                sum += integer % 10;
                integer /= 10;
            }
            return sum;
        }
    }

    /**
     * 10、编写方法实现，给定一个整数，将该数逆置输出
     *   例如给定12345变成54321，123变成321
     */
    static class Tenth {
        public void method () {
            System.out.println(reverse(-12345));
        }
        
        public int reverse (int integer) {
            int newInt = 0;
            while (integer != 0) {
                newInt = newInt * 10 + integer % 10;
                integer /= 10;
            }
            return newInt;
        }
    }

}
