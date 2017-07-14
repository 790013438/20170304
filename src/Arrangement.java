import java.util.Arrays;

public class Arrangement {
    
    public static void main (String[] args) {
        First first = new First();
        
        int[] array = {1, 2, 3};
        first.arrange(array, 0, array.length - 1);
    }

    static class First {
        
        private int count = 0;
        
        public void arrange (int[] array, int leftIndex, int rightIndex) {
            if (leftIndex == rightIndex) {
                System.out.println(Arrays.toString(array));
            }
            for (int i = leftIndex; i <= rightIndex && message("leftIndex:" + leftIndex + " i <= rightIndex " + i+ " <= "  + rightIndex + " : ", i <= rightIndex, array); ++i) {
                swap(array, leftIndex, i);
                arrange(array, leftIndex + 1, rightIndex);
                swap(array, leftIndex, i);
            }
        }
        
        public void method () {
            int[] array = {1, 2};
            print(array, 0, array.length - 1);
            //22
            System.out.println(count);
            System.out.println("---------------------------");
            arrangement(array, 0, array.length - 1);
            //233
        }
        
        public void print (int[] array, int leftIndex, int rightIndex) {
            if (leftIndex == rightIndex) {
                System.out.println(Arrays.toString(array));
                count++;
            }
            for (int i = rightIndex; leftIndex <= i && message("leftIndex <= i " + leftIndex + " <= "  + i + " : ", leftIndex <= i, array); --i) {
                swap(array, leftIndex, i);
                print(array, leftIndex + 1, rightIndex);
                swap(array, leftIndex, i);
            }
        }
        
        public void swap (int[] array, int left, int right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        
        //2
        private boolean message (String passedString, boolean arg0, int[] array) {
            System.out.print(passedString + arg0 + " ");
            System.out.println(Arrays.toString(array));
            return true;
        }
        
        public void arrangement (int[] array, int leftIndex, int rightIndex) {
            if (leftIndex == rightIndex) {
                System.out.println(Arrays.toString(array));
                System.out.println();
            }
//            for (int i = leftIndex; i <= rightIndex && message("i <= rightIndex " + i + " <= "  + rightIndex + " : ", i <= rightIndex, array); ++i) {
            for (int i = leftIndex; i <= rightIndex; ++i) {
                swap(array, i, rightIndex);
                arrangement(array, leftIndex, rightIndex - 1);
                swap(array, i, rightIndex);
            }
        }
        ///233
        ///233
    }
}