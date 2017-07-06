package javagame.intersection;

import java.util.Random;

import javax.swing.JOptionPane;

public class Day7 {
    
    public final static MyRandom myRandom = null;
    
    public static void main (String[] args) {
        Day7 day7 = new Day7();
        //2有问题System.out = null; 为什么不报错
        System.out.println(System.out == null);
//        System.out.println(myRandom.nextRangeInt(3, 4));
        //233
        MyRandom myRandom = day7.new MyRandom();
        for (int i = 0; i < 88; ++i) {
            int integer = 0;
            System.out.println(integer = myRandom.nextRangeInt(3, 6));
            if (integer > 6) {
                JOptionPane.showMessageDialog(null, integer);
            }
        }
    }
    
    class MyRandom extends Random {
        public int nextRangeInt (int startInt, int maxInt) {
            return startInt + nextInt(maxInt - startInt + 1);
        }
        
    }
}
