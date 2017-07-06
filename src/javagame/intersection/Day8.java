package javagame.intersection;

import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Day8 {
    
    public static void main (String[] args) {
        Day8 day8 = new Day8();
        Demo[] demo = new Demo[88];
        char character = 'z';
        for (int i = 0; i < 88; ++i) {
            demo[i] = day8.new Demo("李" + character--);
        }
        System.out.println(Arrays.toString(demo));
        Arrays.sort(demo);
        System.out.println(Arrays.toString(demo));
        System.out.println();
    }

    class Demo implements Comparable{
        
        private String name;
        
        public Demo () {
        }
        
        public Demo (String name) {
            this.name = name;
        }
        
        @Override
        public int compareTo (Object o) {
            Demo demo = (Demo)o;
            return (this.name.compareToIgnoreCase(demo.name));
        }
        
        @Override
        public String toString() {
            return name;
        }

    }
    
}
