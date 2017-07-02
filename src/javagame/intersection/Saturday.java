package javagame.intersection;

import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Saturday {
    public static void main (String[] args) {
        First first = new First();
        first.method();
        System.out.println("------------------------------");
        Second second = new Second();
        second.method();
        System.out.println("------------------------------");
        Third third = new Third();
        third.method();
        System.out.println("------------------------------");
        Fourth fourth = new Fourth();
        fourth.method();
        System.out.println("------------------------------");
        Fifth fifth = new Fifth();
        fifth.method();
        System.out.println("------------------------------");
        Sixth sixth = new Sixth();
        sixth.method();
    }

    /**
     * 1 用1、2、3、4能组成多少无重复数字的三位数
     */
    static class First {
        
        private int count = 0;
        
        public void method () {
            int[] array = {1, 2, 3, 4};
            print(array, 0, array.length - 1);
            System.out.println("用1、2、3、4能组成 "+ count + "个无重复数字的三位数" );
        }
        
        public void print (int[] array, int leftIndex, int rightIndex) {
            if (leftIndex == rightIndex) {
                for (int i = 0; i < array.length - 1; ++i) {
                    System.out.print(array[i]);
                }
                count++;
                System.out.println();
            }
            for (int i = rightIndex; leftIndex <= i; --i) {
                swap(array, leftIndex, i);
                print(array, leftIndex + 1, rightIndex);
                swap(array, leftIndex, i);
            }
        }
        
        public void swap (int[] array, int left, int right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }

    }

    /**
     * 2 有两个正整数a和b，已知a*b=2048，求a、b各为何值时，a+b的值最小
     */
    static class Second {
        public void method () {
            int assume = 2048;
            int storage = 0;
            for (int i = 2; i < 1024; ++i) {
                if (2048 % i == 0) {
                    if (assume > 2048 / i + i) {
                    assume = 2048 / i + i;
                    storage = i;
                    }
                }
            }
            System.out.println("a = " + storage + ", b = " + 2048 / storage + "(或 a = " + 2048 / storage + ", b = " + storage + ")时，a+b的值最小");
        }
    }
    
    /**
     * 3 李先生岁数的平方与他的夫人的岁数之和是1053，而他的
     *   夫人的岁数的平方与他的岁数之和是873，请编写程序计
     *   算李先生及其夫人的岁数各是多少。 
     */
    static class Third {
        public void method () {
            //x^2 + y == 1053;
            //x + y^2 = 873
            for (int i = 1; i < 90; ++i) {
                for (int j = 2; j < 90; ++j) {
                    if (i * i + j == 1053) {
                        if (i + j * j == 873) {
                            System.out.println("李先生和夫人的岁数是：" + i + ", " + j);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * 4 判断一个数字是否是回文数字(即正着读和反着读都一样):
     *   譬如输入1331 打印:这是数字就是螺丝文数字 
     */
    static class Fourth {
        public void method () {
            SwingUtilities.invokeLater(new Runnable() {
                public void run () {
                    String enterString = JOptionPane.showInputDialog("请输入一个数字:");
                    try {
                        int enterInt = Integer.parseInt(enterString);
                        String message = "这个数字 " + enterInt + (judge(enterInt) ? " 是" : "不是") + "回文数字 ";
                            JOptionPane.showMessageDialog(null, message);
                    } catch (Exception e) {}
                }
            });
        }
     
        public boolean judge (int integer) {
            int temp = integer;
            int newInt = 0;
            while (temp != 0) {
                newInt = newInt * 10 + temp % 10;
                temp /= 10;
            }
            return newInt == integer;
        }
    }
    
    /**
     * 5 每个苹果0.8元，第一天买2个苹果，第二天开始，
     *   每天买前一天的2倍，直至购买的苹果个数达到不超过100的最大值。
     *   编写程序求每天平均花多少钱？
     */
    static class Fifth {
        public void method () {
            float sum = 0;
            int days = 0;
            int num = 0;
            while ((num += Math.pow(2.0, days + 1.0)) <= 100) {
                sum = 0.8f * num;
                days++;
                //22
                System.out.println(days + "天花的钱sum: " + sum + "," + "\t\t\t总共买的苹果：" + num);
                //233
            }
            System.out.println("每天平均花 " + sum / days);
        }
    }
    
    /**
     * 6 试编程序，找出1至99之间的全部同构数。
     *   同构数是这样一组数：它出现在平方数的右边。
     *   例如：5是25右边的数，25是625右边的数，5和25都是同构数。
     */
    static class Sixth {
        public void method () {
            for (int i = 4; i * i < 9999; ++i) {
                System.out.print(i * i + "的同构数：" + isomorphism(i * i) + "\t");
            }
        }
        
        public int isomorphism (int integer) {
            int newInt = 0;
            Stack<Integer> stack = new Stack<Integer>();
            while (integer / 10 != 0) {
                stack.push(integer % 10);
                integer /= 10;
            }
            while(!stack.empty()) {
                newInt = newInt * 10 + stack.pop();
            }
            return newInt;
        }
    }
}
