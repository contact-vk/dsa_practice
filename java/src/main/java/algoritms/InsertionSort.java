package algoritms;

/**
 * Implementation of insertion sort
 * Runtime complexity : O(n^2)
 * Space complexity : O(1)
 */
public class InsertionSort {

    public static <T extends Comparable<T>> void sort(final T[] input) {

        for (int i = 1; i < input.length; i++) {

            T elementNeedToBeInserted = input[i];
            boolean shifted = false;
            int j = i;

            for ( ;j > 0; j--) {

                final int compareResult = elementNeedToBeInserted.compareTo(input[j - 1]);
                // Shift all elements to right
                if (compareResult < 0) {
                    input[j] = input[j - 1];
                    shifted = true;
                    continue;
                }
                break;
            }
            // insert the element
            if (shifted) {
                input[j] = elementNeedToBeInserted;
            }
        }
    }
}
