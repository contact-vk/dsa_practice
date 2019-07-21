package algoritms;

import java.util.List;

/**
 * Implementation of bubble sort with complete fast
 * Runtime complexity : 0(n^2)
 * Space complexity : 0(1)
 */
public class BubbleSort {

    public static <T extends Comparable<T>> void sort(final T[] input) {

        for (int i = input.length - 1; i >= 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {

                final int compareResult = input[j].compareTo(input[j + 1]);
                if (compareResult > 0) {
                    swapped = true;
                    final T temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static <T extends Comparable<T>> void sort(final List<T> input) {

        int i = input.size() - 1;
        boolean swapped = false;
        do {
            for (int j = 0; j < i; j++) {

                final int compareResult = input.get(j).compareTo(input.get(j + 1));
                if (compareResult > 0) {
                    swapped = true;
                    final T temp = input.get(j);
                    input.set(j, input.get(j + 1));
                    input.set(j + 1, temp);
                }
            }
        } while (swapped && --i >= 0);
    }
}
