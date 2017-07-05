import java.util.Arrays;

public class QuickSort {

    public static void main() {
        int[] array = { 1, -2, 3, -4, 5, -6, 7 };
        new QuickSort().quickSort(array, 0, array.length - 1);
    }

    public void method() {
        int[] array = { 3, -5, 21, -1, 2 };
        Arrays.sort(array);
        for (int j = 0; j < array.length; ++j) {
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i] < array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    public void selectSort() {
        int[] array = { 1, -2, 3, -4, -5, 6 };
        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    // 22
    public boolean print(String string) {
        System.out.println(string);
        return true;
    }

    // 233
    public int quickSort(int[] array, int startIndex, int endIndex) {
        Arrays.sort(array);
        if (startIndex >= endIndex && print(Arrays.toString(array))) {
            return endIndex;
        }
        int leftIndex = endIndex;
        while (startIndex < endIndex) {
            while (array[startIndex] < array[endIndex]) {
                startIndex++;
            }
            while (array[startIndex] < array[endIndex]) {
                endIndex--;
            }
            swap(array, startIndex, endIndex);
        }
        // 2
        quickSort(array, startIndex, endIndex - 1);
        quickSort(array, quickSort(array, endIndex, endIndex) + 1, endIndex);
        // 233
        return endIndex;
    }

    public void swap(int[] array, int leftIndex, int rightIndex) {
        int temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }
}