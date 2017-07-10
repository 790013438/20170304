package javagame.intersection;

import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/10.
 */
public class Day10_2 {
    
    /**
     * 1、有一段歌词，每句都以空格“  ”结尾，请将歌词每句按行输出
     */
    class First {

        public void method () {
            String string = "长亭外 古道边 芳草碧连天 晚风拂 柳笛声残 夕阳山外山";
            String[] tokens = string.split("\\p{Zs}+");
            System.out.println("***原歌词格式***%n%n");
            System.out.println(string);
            System.out.println("***拆分后歌词格式***");
            for (int i = 0; i < tokens.length; ++i) {
                System.out.println(tokens[i] + "  ");
            }
            System.out.println("  ");
        }

    }
    /**
     * 2、判断.java文件名是否正确，判断邮箱格式是否正确
     */
    class Second {

        public void method () {
            System.out.println("---欢迎进入作业提交系统---");
            System.out.println("请输入Java文件名：");
            Scanner scanner = new Scanner(System.in);
            String enterString = scanner.nextLine();
            if (enterString.endsWith(".java")) {
                System.out.println("请输入你的邮箱：");
                String enterEmail = scanner.nextLine();
                if (enterEmail.matches("[\\p{L}.*\\p{N}]+@\\p{L}+.\\p{L}+")) {
                    System.out.println("作业提交成功");
                } else {
                    System.out.println("作业提交失败!");
                }
            } else {
                System.out.println("java文件名错误");
            }
        }

    }

    /**
     * 3、将一个数字字符串转换成逗号分隔的数字串，即从右边开始每三个数字用逗号分隔
     * 提示：利用StringBuffer类的length()和insert  ()方法实现需求
     */
    class Third {

        public void method () {
            String string = "12345678";
            StringBuffer stringBuffer = new StringBuffer(string);
        }

    }

    /**
     * 4、验证身份证号、手机号、座机号格式是否正确(要求看下图)
     * 判断座机的电话号码时，按照字符“-”符号进行拆分，然后判断长度
     */
    /**
     * 5、输入一个 URL 地址，自定义方法提取出它使用的协议（HTTP 协议或 FTP 协议等）
     */
    
    public static void main (String[] args) {
        /**
         * 1、有一段歌词，每句都以空格“  ”结尾，请将歌词每句按行输出
         */
        /**
         * 2、判断.java文件名是否正确，判断邮箱格式是否正确
         */
        /**
         * 3、将一个数字字符串转换成逗号分隔的数字串，即从右边开始每三个数字用逗号分隔
         * 提示：利用StringBuffer类的length()和insert  ()方法实现需求
         */
        /**
         * 4、验证身份证号、手机号、座机号格式是否正确(要求看下图)
         * 判断座机的电话号码时，按照字符“-”符号进行拆分，然后判断长度
         */
        /**
         * 5、输入一个 URL 地址，自定义方法提取出它使用的协议（HTTP 协议或 FTP 协议等）
         */
    }
    
}
