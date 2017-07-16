package javagame.threads;

/**
 * Created by 79001 on 2017/7/16.
 */
public class Saturday3 {
    /**
     * 1、已知字符串："this is a test of java"
     * 按要求执行以下操作：
     * (1) 统计该字符串中字母s出现的次数
     * (2) 取出子字符串"test"
     * (3) 将本字符串复制到一个字符数组Char[] str中.
     * (4) 将字符串中每个单词的第一个字母变成大写， 输出到控制台。
     * (5) 用两种方式实现该字符串的倒序输出。(用StringBuffer和for循环方式分别实现)
     */
    class First {
        public String string;
        
        public void method () {
            string = "this is a test of java";
//            * (1) 统计该字符串中字母s出现的次数
            int count = 0;
            for (int i = 0; i < string.length(); ++i) {
                if ('s' == string.charAt(i)) {
                    count++;
                }
            }
            System.out.println("s的次数：" + count);
//            * (2) 取出子字符串"test"
            int index = string.indexOf("test");
            System.out.println(string.substring(index, index + "test".length()));
//            * (3) 将本字符串复制到一个字符数组Char[] str中.
            char[] str = string.toCharArray();
            System.out.println(str);
//            * (4) 将字符串中每个单词的第一个字母变成大写， 输出到控制台。
            String[] array = string.split("[\\p{Z}\\p{P}]+");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                String tempStr = new Character(array[i].charAt(0)).toString();
                tempStr = tempStr.toUpperCase();
                stringBuilder.append(tempStr);
                stringBuilder.append(array[i].substring(1)).append(" ");
            }
            System.out.println(stringBuilder);
//            * (5) 用两种方式实现该字符串的倒序输出。(用StringBuffer和for循环方式分别实现)
            StringBuilder stringB = new StringBuilder(string);
            System.out.println(stringB.reverse());
            //第2种
            stringB.delete(0, stringB.length());
            for (int i = string.length() - 1; i >= 0; --i) {
                stringB.append(string.charAt(i));
            }
            System.out.println(stringB);
        }
    }
    
    /**
     * 2、初始化一个字符串“ABCDEFG”,然后分别在后面跟上自己的小写字母.
     * A(a)B(b)C(c)D(d)E(e)F(f)G(g)
     */
    class Second {
        
        public void method () {
            String string = "ABCEDFG";
            StringBuilder stringBuilder = new StringBuilder(string);
            
            //从后往前插
            for (int i = stringBuilder.length(); i > 0; --i) {
                //转成小写
                String str = new Character(string.charAt(i - 1)).toString();
                str = str.toLowerCase();
                
                //插入
                stringBuilder.insert(i, "(" + str + ")");
            }
            System.out.println(stringBuilder);
        }
        
    }
    
    /**
     * 3、已知字符串String s = "ljf55dfal12sdjflkas88djfa43a899"; 要求：把字符串中的所有数字存入int型数组中。并对数组进行排序。(55算是一个数字)
     */
    class Third {
        
        public void method () {
        
        }
        
    }
    
    public static void main (String[] args) {
        Saturday3 saturday3 = new Saturday3();
        
        First first = saturday3.new First();
        first.method();
        
        Second second = saturday3.new Second();
        second.method();
    }
    
}
