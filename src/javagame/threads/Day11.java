package javagame.threads;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.function.*;


/**
 * Created by 79001 on 2017/7/11.
 */
public class Day11 {
    
    /**
     * 1、商场为了促销商品，常常采用有奖销售的方法刺激顾客消费，在五一劳动节期间，
     * 某商场采取了凭当天购物发票抽奖的方式促销商品，
     * 奖金分为1元、5元、10元、20、30、40、50、60、70、80、90元和100元等12种，
     * 试编写程序，若抽中某一奖项，则输出奖项额，否则输出没中奖的信息。
     */
    class First {
    
        public void method () {
        
            int[] bonusArray = {1, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
            Random random = new Random();
            int integer = random.nextInt(24);
            if (integer >= 0 && integer < bonusArray.length) {
                System.out.println("奖金为：" + bonusArray[integer]);
            } else {
                System.out.println("没有中奖");
            }
        }
    
    }
    
    /**
     * 2、编写一个方法：double round(double m ,int n)将m四舍五入 到小数点后第n位。
     */
    class Second {

        public double round (double m, int n) {
            if (n > 0) {
                m *= Math.pow(10, n);
                m = Math.round(m);
                m /= Math.pow(10, n);
            }
            return m;
        }

    }
 
    /**
     * 3、设计一个方法，返回当前时间距离北京奥运会(2018年1月1日)相距的天数。
     */
    class Third {
    
        /**
         * java8的新内容
         */
        public void method () {
            System.out.println("当前时间距离北京奥运会(2018年1月1日)相距的天数");
            LocalDate localDate = LocalDate.now();
            System.out.println(ChronoUnit.DAYS.between(localDate, LocalDate.of(2017, Month.JULY, 14)));
        }

    }
 
    /**
     * 4、输入自己的出生日期，计算自己的年龄
     */
    class Fourth {
    
        /**
         * java8新内容
         */
        public void method () {
            LocalDate localDate = LocalDate.now();
            long years = ChronoUnit.YEARS.between(LocalDate.of(1995, Month.FEBRUARY, 22), localDate);
            System.out.println("年龄:" + years);
        }

    }

    /**
     * 5、打印未来几年的10个黑色星期五（按照 2010-10-11格式把年月日打印出来）
     */
    class Fifth {
    
        public void method () {
            System.out.println("黑色星期五");
            LocalDate localDate = LocalDate.of(2010, Month.NOVEMBER, 11);
            for (int i = 0; i < 10; ++i) {
                localDate = localDate.with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.THURSDAY));
                localDate = localDate.plusDays(1);
                System.out.println(localDate.getYear() + "-" +localDate.getMonthValue() + "-" + localDate.getDayOfMonth());
                localDate = localDate.plusYears(1);
            }
        }

    }
    
    /**
     * 6、编写一个Teacher类（属性： 教员姓名、工号、授课方向、年龄）
     * 重写toString()  返回教员信息
     * 重写equals()    要求只要教员姓名、工号一致，就是同一个对象
     * 重写hashCode()  返回所有属性的hashCode编码和
     */
    class Sixth {
        
        private String name;
        private String id;
        private String direction;
        private int age;
    
        public void method () {
        
        }
    
        @Override
        public String toString() {
            return "Sixth{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", direction='" + direction + '\'' +
                    ", age=" + age +
                    '}';
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Sixth sixth = (Sixth) o;
        
            if (age != sixth.age) return false;
            if (name != null ? !name.equals(sixth.name) : sixth.name != null) return false;
            if (id != null ? !id.equals(sixth.id) : sixth.id != null) return false;
            return direction != null ? direction.equals(sixth.direction) : sixth.direction == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            result = 31 * result + (direction != null ? direction.hashCode() : 0);
            result = 31 * result + age;
            return result;
        }
    }

    /**
     * 7、输入一个日期，输出该月的一个日历格式
     */
    class Seventh {
        
        public void method () {
            LocalDate localDate = LocalDate.ofEpochDay(3);
            System.out.println(localDate.getDayOfWeek());
        }
        
    }
    
    public static void main (String[] args) {
    
        Day11 day11 = new Day11();
        First first = day11.new First();
        first.method();
        
        Second second = day11.new Second();
        System.out.println(second.round(3.14215, 3));
        
        Third third = day11.new Third();
        third.method();
        
        Fourth fourth = day11.new Fourth();
        fourth.method();
        
        Fifth fifth = day11.new Fifth();
        fifth.method();
        
        Seventh seventh = day11.new Seventh();
        seventh.method();
        
        Integer[] intArray = {13, 23, 2, 1, 34, 12, 98, 24};
        Arrays.sort(intArray, (o1, o2) -> {return o2 - o1;});
        System.out.println(Arrays.toString(intArray));
        
        BiPredicate<String, String> biPredicate = String::equals;
        boolean b = biPredicate.test("New", new String("New"));
        System.out.println(b);
        
        Function<String, StringBuffer> fun1 = (n) -> {return new StringBuffer(n);};
        Function<String, StringBuffer> fun2 = StringBuffer::new;
        System.out.println(fun2.apply("qwerty"));
        
        Collection arrayList = new ArrayList();
        for (int i = 0; i < 3; ++i) {
            arrayList.add("qwerty");
        }
        System.out.println(arrayList);
        
    }
    
}
