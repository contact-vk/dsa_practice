package algoritms;

/**
 * Implementation of selection sort
 * Runtime complexity : O(n^2)
 * Space complexity : O(1)
 */
public class SelectionSort {

    public static <T extends Comparable<T>> void sort(final T[] input) {

        for (int i = 0; i < input.length; i++) {

            T min = input[i];
            int minIndex = i;

            for (int j = i+1; j < input.length; j++) {

                final int compareResult = min.compareTo(input[j]);
                if (compareResult > 0) {
                    min = input[j];
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                T temp = input[i];
                input[i] = min;
                input[minIndex] = temp;
            }
        }
    }
}
