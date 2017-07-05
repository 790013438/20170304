package javagame.intersection;

import java.util.Random;

import javax.swing.JOptionPane;

public class Day7 {
    
    public static void main (String[] args) {
        Day7 day7 = new Day7();
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
