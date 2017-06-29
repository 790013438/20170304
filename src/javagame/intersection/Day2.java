package javagame.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main (String[] args) {
        int a = 3;
        int b = 4;
        Day2 w = new Day2();
//        w.firstMethod(a, b);
//        w.secondMethod(a, b);
//        w.thirdMethod(a, b);
//        233

        //2
        Second second = new Second();
//        second.firstMethod(a, b);
//        second.secondMethod(a, b);
//        233

        //3
        Third third = new Third();
//        third.Method();
        
        //4
        Fourth fourth = new Fourth();
//        fourth.method();
        
        Comparision comparision = new Comparision();
        comparision.method();
//        233
//        comparision.secondMethod();
    }

    public void firstMethod (int a, int b) {
        System.out.println((a++) / 3 + (--b) * 2 - (a--) % 6 + (b++) * 3 - (b--));
    }

    public void secondMethod (int a, int b) {
        System.out.println((++b) * 2 - (a--) % 4 + (a++) * 5 - (--b) / 2 + (--a));
    }

    public void thirdMethod (int a, int b) {
        System.out.println((a--) * 6 + (b++) / 3 - (--a) * 2 - (--b) * 2 + (++a));
    }

    //2第二题
    
    static class Second {
        public void firstMethod (int a, int b) {
            System.out.println(a > b & (a++) > b & (--b) < a & (b++) > (a--));
        }
        
        public void secondMethod (int a, int b) {
            System.out.println(b <= a | (b--) > (--a) | (++b) != (a++) | (b--) == (a--));
        }
    }

    //3第三题
    static class Third {
        int x = 4, y = 2, z = 3;
        public void Method () {
            System.out.println(y -= z++ * --x);
            System.out.println("x = " + x + ", y = " + y + ", z = " + z);
        }
    }

    //4第四题
    static class Fourth {
        public void method () {
            double T_shirtPriceDouble = 0;
            double TennisShoesPriceDouble = 0;
            double TennisRacketPriceDouble = 0;
            int T_shirtAmount = 0;
            int shoesAmount = 0;
            int racketAmount = 0;
            double sum = 0;
            double item = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入T恤价格:");
            T_shirtPriceDouble = scanner.nextDouble();
            System.out.println("请输入网球鞋价格:");
            TennisShoesPriceDouble = scanner.nextDouble();
            System.out.println("请输入网球拍价格:");
            TennisRacketPriceDouble = scanner.nextDouble();
            System.out.println("请输入购买T恤购买数量:");
            T_shirtAmount = scanner.nextInt();
            System.out.println("请输入购买网球鞋的数量:");
            shoesAmount = scanner.nextInt();
            System.out.println("请输入购买网球拍的数量：");
            racketAmount = scanner.nextInt();
            System.out.println("********************消费单********************");
            System.out.println("购买物品    单价        个数        金额");
            System.out.println(String.format("%s\t %-8.1f%-3d%6.1f", "T恤", T_shirtPriceDouble, T_shirtAmount, item = T_shirtPriceDouble * T_shirtAmount));
            sum += item;
            System.out.println(String.format("%s\t %-8.1f%-3d%6.1f", "网球鞋", TennisShoesPriceDouble, shoesAmount, item = TennisShoesPriceDouble * shoesAmount));
            sum += item;
            System.out.println(String.format("%s\t %-8.1f%-3d%6.1f", "网球拍", TennisRacketPriceDouble, racketAmount, item = TennisRacketPriceDouble * racketAmount));
            sum += item;
            System.out.println("**********************************************");
            System.out.println("折扣：     8.0折");
            System.out.println("消费总额：          ￥" + sum * 0.8);
        }
    }

    static class Comparision {
        public void method () {
            for (int i = 0; i < 10; ++i) {
                int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                int leftIndex = 0;
                int rightIndex = array.length - 1;
                int middleIndex = 0;
                //2
                int count = 0;
                //233
                while (leftIndex <= rightIndex) {
                    //2
                    System.out.println(middleIndex + "(middleIndex) <=赋值 (" + leftIndex + " + " + rightIndex + ") / 2 = " + ((leftIndex + rightIndex) / 2));
                    if (count++ > 10) {
                        break;
                    }
                    //233
                    middleIndex = (rightIndex + leftIndex) / 2;
                    //2
//                    System.out.println("array[middleIndex] < i <----------------------->" + array[middleIndex] +" < " + i + (array[middleIndex] < i));
                    //233
                    if (array[middleIndex] < i) {
                        leftIndex = middleIndex + 1;
                    } else if (i < array[middleIndex]) {
                        rightIndex = middleIndex - 1;
                    } else {
                        break;
                    }
                }
                System.out.println((array[middleIndex] == i) + "\tarray[" + middleIndex + "](" + array[middleIndex] + ") == " + i);
                //2
                for (int j = 0; j < array.length; ++j) {
                    System.out.print(array[j] + ", ");
                }
                System.out.println();
                //2333
            }
        }
        
        public void secondMethod () {
            int sum = 0;
            for (int i = 0; i < 11; ++i) {
                sum += i * 5;
                System.out.println(i * 5);
            }
            for (int i = 0; i < 26; ++i) {
                if (i % 5 != 0)
                    sum += i * 2;
                System.out.println(i * 2);
            }
            System.out.println(sum);
        }
    }
}
