package javagame.intersection;

import java.util.Scanner;

public class Day5 {
    
    static {
        System.out.println("static Day5");
    }
    
    public static void print () {
        //Do nothing
    }
    
    public static void main (String[] args) {
//        Server first = new Server();
//        char[] charArray = {'a', 'c', 'u', 'b', 'e', 'p', 'f', 'z'};
//        first.method(charArray);
//        Second second = new Second();
//        second.method();
        Third third = new Third();
        third.method();
        Fourth fourth = new Fourth();
        fourth.method();
        //233
//        Sixth sixth = new Sixth();
//        sixth.method();
        Wrapper wrapper = (new Day5()).new Wrapper();
        wrapper.whileMethod();
        wrapper.doWhileMethod();
    }

    class Wrapper {
        public void whileMethod () {
            int maxValue = 10;
            int nextInt = 1;
            int sum = 0;
            int product = 1;
            while (nextInt <= maxValue) {
                if (nextInt % 2 == 0) {
                    sum += nextInt;
                } else {
                    product *= nextInt;
                }
                nextInt++;
            }
            System.out.println("sum: " + sum +"\tproduct: " + product);
        }

        public void doWhileMethod () {
            int maxValue = 10;
            int nextInt = 1;
            int sum = 0;
            int product = 1;
            do {
                if (nextInt % 2 == 0) {
                    sum += nextInt;
                } else {
                    product *= nextInt;
                }
                nextInt++;
            } while (nextInt <= maxValue);
            System.out.println("sum: " + sum + "\tproduct:" + product);
        }
    }
    
    /**
     * 1、有一列乱序的字符，‘a’,‘c’,‘u’,‘b’,‘e’,‘p’,‘f’,‘z’，排序并按照英文字母表的逆序输出（要求自己写算法）
     * @author 79001
     *
     */
    static class First {
        
        public void method (char[] array) {
            for (int j = 0; j < array.length; ++j) {
                for (int i = 0; i < array.length - 1; ++i) {
                    if (array[i] < array[i + 1]) {
                        char temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < array.length; ++i) {
                System.out.print(array[i] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 2、有一个数列：8，4，2，1，23，344，12
     * 1）循环输出数列的值
     * 2）求数列中所有数值的和
     * 3）从键盘中任意输入一个数据，判断数列中是否包含此数
     * @author 79001
     *
     */
    static class Second {
        int[] array = {8, 4, 2, 1, 23, 344, 12};
        public void method () {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum += array[i];
                System.out.print(array[i] + "\t");
            }
            System.out.println("\n数列中所有数值的和:" + sum);
            System.out.println("请输入一个数据：");
            int enterInt = new Scanner(System.in).nextInt();
            for (int i = 0; i < array.length; ++i) {
                if (enterInt == array[i]) {
                    System.out.println("数列中是包含此数");
                    return;
                }
            }
            System.out.println("数列中不包含此数");
        }
    }

    /**
     * 3 小明要去买一部手机，他询问了4家店的价格，
     *   分别是2800元，2900元，2750元和3100元，
     *   显示输出最低价，并告知是第几家店
     */
    static class Third {
        public void method () {
            int[] array = {2800, 2900, 2750, 3100};
            int minInt = array[0];
            int index = 0;
            for (int i = 0; i < array.length; ++i) {
                if (minInt > array[i]) {
                    minInt = array[i];
                    index = i;
                }
            }
            System.out.println("最低价：" + minInt + "，第" + (index + 1) + "店");
        }
    }

    /**
     * 4、向一个有序数组中插入一个数据后，仍保持该数组有序。
     *    提示：找到该数应存放的位置后，移动该位置后面的所有元素后，把该元素放到该位置上。
     * @author 79001
     *
     */
    static class Fourth {
        public int[] array = {1, 2, 4, 5, 6, 8, 10};
        public void method () {
            int[] array = {1, 2, 4, 5, 6, 8, 10};
            //22
            for (int i: insert(array, 3)) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        public int[] insert (int[]array, int integer) {
            int index = array.length;
            int[] newArray = new int[array.length + 1];
            for (int i = 1; --index >= 0 && integer <= array[index]; ++i) {
                newArray[newArray.length - i] = array[index];
            }
            newArray[index + 1] = integer;
            for (int i = index; i >=0; --i,--index) {
                newArray[i] = array[index];
            }
            return newArray;
        }
    }
    
    /**
     * 5、有一个已经排好序的数组。要求输入一个数，在数组中查找是否有这个数，
     *    如果有，将该数从数组中删除，要求删除后的数组仍然保持有序；
     *    如果没有，则输出“数组中没用这个数！”
     * @author 79001
     *
     */
    static class Fifth {
        public void method () {
            int[] array = {1, 2, 2, 3, 4, 5, 6, 7};
        }
        
        public void delete (int[] array, int integer) {
            for (int i = 0; i < array.length; ++i) {
               if (integer == array[i]) {
                   //delete
               } 
            }
        }
    }
    
}
