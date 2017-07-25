package javagame.intersection;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Day1 {
    
//    public void delete (File file) {
//        if (file.listFiles() != null ) {
//            for (File f : file.listFiles()) {
//                if (f.isDirectory()) {
//                    delete(f);
//                }
//                try {
//                    f.delete();
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }
//        file.delete();
//    }
    
    public static void main (String[] args) {
        
        final Day1 day1 = new Day1();
        
        File file = new File("20170304\\src\\javagame\\intersection\\Day1.java");
        file = new File("20170304\\src\\javagame\\intersection\\test.java");
        System.out.println(file.exists());
        System.out.println(file.getParentFile().mkdirs());
        try {
            System.out.println(file.createNewFile());
        } catch (Exception e) {
            System.out.println(e);
        }
        if (file.exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                String string = String.format("%n%s", "//hello FileOutputStream 你好qwerty");
                fileOutputStream.write(string.getBytes());
                System.out.println("写入成功");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
//        FileInputStream fileInputStream = null;
//        if (file.exists()) {
//            try {
//                fileInputStream = new FileInputStream(file);
//                byte[] bufferByte = new byte[1024];
////            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bufferByte);
//                while (fileInputStream.read(bufferByte) > 0) {
//                    System.out.print(new String(bufferByte));
//                }
//            } catch (IOException e) {
//                System.out.println(e);
//            } catch (Exception e) {
//                System.out.println(e);
//            } finally {
//                try {
//                    if (fileInputStream != null) {
//                        fileInputStream.close();
//                    }
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }
        
//        File file = new File("d:\\a\\hello.txt");
//        System.out.println(file.getParentFile().mkdirs());
//        try {
//            System.out.println(file.createNewFile());
//        } catch (Exception e) {System.out.println(e);}
//        new Scanner(System.in).next();
//        ///233
//        day1.delete(file.getParentFile());
        
//        File file =new File("asf");
//
//        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        for (int j = 1; j < array.length; ++j) {
//            for (int i = 0; i < array.length - j; ++i) {
//                day1.swap(array, i, i + 1);
//                System.out.println(Arrays.toString(array));
//            }
//        }
//
//        Server first = new Server();
//        first.method();
//        Second second = new Second();
//        second.method();
        Third third = new Third();
        third.method();
        third.method();
        /**
         * 不用辅助变量交换
         * 我左手提了10斤，右手提了5斤。
         * 现在，把右手的5斤放到左手，左手提了15斤，明显感觉重很多，不行换过来。
         * 然后，右手以前提5斤，现在提10斤 = （左手15 - 5斤）。
         * 左手5斤 = 15 - 10 斤
         */
        third.exchange();
//        Fourth fourth = new Fourth();
//        fourth.firstMethod();
//        fourth.secondMethod();
//        fourth.thirdMethod();
//        fourth.fourthMethod();
//        fourth.fifthMethod();
//        fourth.sixthMethod();
//        fourth.seventhMethod();
//        fourth.eighth1Method();
//        fourth.eighth2Method();
//        fourth.nithMethod();
//        fourth.tenthMethod();
//        fourth.eleventhMethod();
//        fourth.twelfthMethod();
    }
    
    public void swap (int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }
    

    //1第一题
    static class First {
        public void method () {
            System.out.println("Floyd, 22, 790013438, Millitary");
        }
    }

    //2第二题
    static class Second {
        public void method () {
            System.out.println("int float double boolean char long byte");
        }
    }

    //3第三题
    static class Third {
        int a = 4;
        int b = 8;
        int temp;
        public void method () {
            System.out.println("a = " + a + ", b = " + b + ", 进行交换");
            temp = a;
            a = b;
            b = temp;
            System.out.println("结果：a = " + a + ", b = " + b);
        }

        //不用辅助变量，交换两个整数
        public void exchange () {
            System.out.println("a = " + a + ", b = " + b + ", 进行交换(不用辅助变量)");
            a = a + b;
            b = a - b;
            a = a -b;
            System.out.println("结果：a = " + a + ", b = " + b);
        }
    }

    //4第四题
    static class Fourth {
        
        //1小题
        public void firstMethod () {
            System.out.println(23 + 10 - 6);
        }
        
        //2小题
        public void secondMethod () {
            System.out.println(36 - 18 - 9);
        }

        //3小题
        public void thirdMethod () {
            System.out.println(160 - 25 + 160);
        }

        //4小题
        public void fourthMethod () {
            System.out.println(2 * 8 + 14);
        }

        //5小题
        public void fifthMethod () {
            System.out.println(28 - 4 * 5);
        }

        //6小题
        public void sixthMethod () {
            System.out.println(3 * 6 - 13);
        }

        //7小题
        public void seventhMethod () {
            int integer = 256;
            System.out.println(integer / 100 + integer % 10);
        }

        //8.1小题
        public void eighth1Method () {
            int a = 5;
            int b = 0, c = 0;
            b = a++;
            c = ++a;
            c++;
            System.out.println("预计a = 7, b = 5, c = 8");
            System.out.println("结果a = " + a + ", b = " + b + ", c = " + c);
        }

        //8.2小题
        public void eighth2Method () {
            int a = 7;
            int b = 0;
            int c = 0;
            a--;
            b = a--;
            c = --b;
            System.out.println("预计a = 5, b = 5, c = 5");
            System.out.println("结果a = " + a + ", b = " + b +", c = " + c);
        }

        //9小题
        public void nithMethod () {
            int oddInt = 7;
            int evenInt =8;
            System.out.println(oddInt +(oddInt % 2 == 0 ? "是":"不是") + "偶数");
            System.out.println(evenInt + (evenInt % 2 == 0 ? "是":"不是") + "偶数");
        }

        //10小题
        public void tenthMethod () {
            int x = 3;
            int y = 4;
            int maxInt = x > y ? x : y;
            System.out.println(x + "和" + y + "最大的是：" + maxInt);
            int z = 2;
            maxInt = (x > y ? x : y) > z ? (x > y ? x : y) : z;
            System.out.println(x + ", " + y + "和" + z + "最大的是：" + maxInt);
        }

        //11小题
        public void eleventhMethod () {
            double floatingDouble = 456.78;
            System.out.println("下面是11");
            System.out.println((int)floatingDouble % 10 + (int)floatingDouble / 10 % 10);
        }

        //12小题
        public void twelfthMethod () {
            System.out.println("int a = 26;");
            System.out.println("float f = 128.3 + a;");
            System.out.println("编译不通过, 因为128.3默认是double，计算结果数据类型是double，不能赋值给float类型。在128.3后加f，表示float类型");
            int a = 26;
            float f = 128.3f + a;
            short s = Short.MAX_VALUE;
            System.out.println("short max value:" + s);
        }
    }
}
