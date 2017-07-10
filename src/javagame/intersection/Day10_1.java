package javagame.intersection;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/10.
 */
public class Day10_1 {
   
    /**
     * 1.初始化一个字符串，将其按照顺序输出再按照字符串的倒序输出字符串。
     * 例如  String str="abcdef";
     * 先输出 abcdef   再输出 fedcba
     */
    class First {
    
        public void method () {
            String str = "abcdef";
            System.out.println(str);
            System.out.println(new StringBuffer(str).reverse());
        }
        
    }
    
    /**
     * 2.一个字符串当中含有大写字母和小写字母。将字符串都变成大写字母输出一次，再将字符串都变成小写字母输出。
     * 例如 String str="AbcDef"
     * 先输出 ABCDEF 然后输出 abcdef
     */
    class Second {
        
        public void method () {
            String str = "AbcDef";
            System.out.println(str.toUpperCase());
            System.out.println(str.toLowerCase());
        }
    }
    
    /**
     * 3.判断字符串是否以“a”结尾，如果是则输出此字符串，如果不是，则将字符串改成以“a”为结尾的字符串。
     * 例如  String str="sdfa"; 则直接输出
     * String str="sdfc"; 则输出sdfa
     */
    class Third {
        
        public void method () {
            String str = "sdfa";
            System.out.println(!str.endsWith("a") ? str : str.substring(0, str.length() - 1) + "a");
        }
        
    }
    
    /**
     * 4.输入一行字符串，分别统计出其中英文字母、空格、数字和其它字符的个数。
     */
    class Fourth {
        
        public void method () {
            int letter = 0;
            int space = 0;
            int digital = 0;
            int other = 0;
            String enterString = "";
            System.out.println("请输入一行字符串：");
            Scanner scanner = new Scanner(System.in);
            try {
                enterString = scanner.nextLine();
            } catch (Exception e) {}
            char[] tokens = enterString.toCharArray();
            for (char character : tokens) {
                if (character >= 'A' && character <= 'z') {
                    letter++;
                } else if (character == ' ') {
                    space++;
                } else if (character >= '0' && character <= '9') {
                   digital++;
                } else {
                    other++;
                }
            }
            System.out.println("\tletter" + "\tspace" + "\tdigital" + "\tother");
            System.out.println("\t" + letter + "\t" + space + "\t" + digital + "\t" + other);
        }
        
    }
    /**
     * 5.假设有字符串“asadabbcaerw” 以a当作分隔符 将字符串分成4个子串 s d bbc erw
     */
    class Fifth {
        
        public void method () {
            String string = "asadabbcaerw”";
            System.out.println(Arrays.toString(string.split("[a]+")));
        }
        
    }
    
    /**
     * 6.输入一个字符串,去除其中所有空格后,输出.
     * 例如  String str="a b c"; 输出 abc
     */
    class Sixth {
        
        public void method () {
            String str = "a b c";
            System.out.println(str.replaceAll("\\p{Zs}", ""));
        }
        
    }
    
    /**
     * 7.输入一行字符串,求出其中'a'的个数.
     * 例如  String str="abacad";   3个
     */
    class Seventh {
        
        public void method () {
            String str="abacad";
            int count = 0;
            for (int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) == 'a') {
                    count++;
                }
            }
            System.out.println("a的个数：" + count);
        }
        
    }
    
    /**
     * 8.给出任意一段字符串如"hello world is the first lesson for every programming language!"
     * 要求在控制台按位置对应输入相应的字符,输入结束后统计错误字符个数.
     */
    class Eighth {
        
        public void method () {
            String standardString = "hello world is the first lesson for every programming language!";
            int count = 0;
            System.out.println("请输入一摸一样的句子：hello world is the first lesson for every programming language!");
            Scanner scanner = new Scanner(System.in);
            String enterString = scanner.nextLine();
            if (enterString.length() == standardString.length()) {
                for (int i = 0; i < enterString.length(); ++i) {
                   if (enterString.charAt(i) != standardString.charAt(i)) {
                       count++;
                   }
                }
            } else {
                System.out.println("没有输入完整");
            }
            System.out.println("错误字符个数：" + count);
        }
        
    }
    
    /**
     * 9.输出指定字符串的指定位置的字母。
     * "abcxyz" bc的位置：2
     */
    class Ninth {
        
        public void method () {
            String string = "abcxyz";
            System.out.println("abcxyz, bc的位置：" + (string.indexOf("bc") + 1));
        }
        
    }
    
    /**
     * 10.判断两个字符串内容是否一样，忽略大小写。如  aaa和AaA 输出 true
     */
    class Tenth {
        
        public void method () {
            String string1 = "aaa";
            String string2 = "AaA";
            System.out.println("\t判断两个字符串内容是否一样，忽略大小写。");
            System.out.println("\taaa 和 AaA:" + string1.equalsIgnoreCase(string2));
        }
        
    }
    
    /**
     * 11.将字符串 “abcdefg” 中的 “de” 截取出来。
     */
    class Eleventh {
        
        public void method () {
            String string = "abcdefg";
            int index = string.indexOf("de");
            System.out.println(string.substring(index, index + "de".length()));
        }
        
    }
    
    /**
     12.查找一个字符串中是否包含字符’a‘
     */
    class Twelfth {
        
        public void method () {
            String string = "qwerty";
            if (string.indexOf("a") > 0) {
                System.out.println("包含a");
            } else {
                System.out.println("不包含a");
            }
        }
        
    }
    
    public static void main(String[] args) {
    
        Day10_1 day10 = new Day10_1();
        /**
         * 1.初始化一个字符串，将其按照顺序输出再按照字符串的倒序输出字符串。
         * 例如  String str="abcdef";
         * 先输出 abcdef   再输出 fedcba
         */
        First first = day10.new First();
        first.method();
        /**
         * 2.一个字符串当中含有大写字母和小写字母。将字符串都变成大写字母输出一次，再将字符串都变成小写字母输出。
         * 例如 String str="AbcDef"
         * 先输出 ABCDEF 然后输出 abcdef
         */
        Second second = day10.new Second();
        second.method();
        /**
         * 3.判断字符串是否以“a”结尾，如果是则输出此字符串，如果不是，则将字符串改成以“a”为结尾的字符串。
         * 例如  String str="sdfa"; 则直接输出
         * String str="sdfc"; 则输出sdfa
         */
        Third third = day10.new Third();
        third.method();
        /**
         * 4.输入一行字符串，分别统计出其中英文字母、空格、数字和其它字符的个数。
         */
//        Fourth fourth = day101.new Fourth();
//        fourth.method();
        /**
         * 5.假设有字符串“asadabbcaerw” 以a当作分隔符 将字符串分成4个子串 s d bbc erw
         */
        Fifth fifth = day10.new Fifth();
        fifth.method();
    
        /**
         * 6.输入一个字符串,去除其中所有空格后,输出.
         * 例如  String str="a b c"; 输出 abc
         */
        Sixth sixth = day10.new Sixth();
        sixth.method();
    
        /**
         * 7.输入一行字符串,求出其中'a'的个数.
         * 例如  String str="abacad";   3个
         */
        Seventh seventh = day10.new Seventh();
        seventh.method();
    
        /**
         * 8.给出任意一段字符串如"hello world is the first lesson for every programming language!"
         * 要求在控制台按位置对应输入相应的字符,输入结束后统计错误字符个数.
         */
//        Eighth eighth = day101.new Eighth();
//        eighth.method();
    
        /**
         * 9.输出指定字符串的指定位置的字母。
         * "abcxyz" bc的位置：2
         */
        Ninth ninth = day10.new Ninth();
        ninth.method();
    
        /**
         * 10.判断两个字符串内容是否一样，忽略大小写。如  aaa和AaA 输出 true
         */
        Tenth tenth = day10.new Tenth();
        tenth.method();
    
        /**
         * 11.将字符串 “abcdefg” 中的 “de” 截取出来。
         */
        Eleventh eleventh = day10.new Eleventh();
        eleventh.method();
    
        /**
         12.查找一个字符串中是否包含字符’a‘
         */
        Twelfth twelfth = day10.new Twelfth();
        twelfth.method();
    
    }
    
}
