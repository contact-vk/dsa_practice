package algoritms;

/**
 * Implementation of merge sort
 * Runtime complexity : O(n * log(n))
 * Space complexity : O(1) as it is doing in place swaps
 */
public class MergeSort {

    public static <T extends Comparable<T>> void sort(final T[] input) {
        sort(input, 0, input.length - 1);
    }

    private static <T extends Comparable<T>> void sort(final T[] input, final int startIndex, final int endIndex) {

        if (startIndex < endIndex) {
            final int middlePoint = startIndex + (endIndex - startIndex) / 2;
            sort(input, 0, middlePoint);
            sort(input, middlePoint + 1, endIndex);

            merge(input, startIndex, middlePoint + 1, endIndex);
        }
    }

    private static <T extends Comparable<T>> void merge(final T[] input,
                                                        final int startIndexOfFirstSubArray,
                                                        final int startIndexOfAnotherSubArray,
                                                        final int endIndex) {

        int index1 = startIndexOfFirstSubArray;
        int index2 = startIndexOfAnotherSubArray;

        int endIndex1 = startIndexOfAnotherSubArray - 1;

        while (index1 <= endIndex1 && index2 <= endIndex) {

            final int compareResult = input[index1].compareTo(input[index2]);

            // element at index1 < element at index2 i.e element at index1 is at right position.
            if (compareResult <= 0) {
                index1++;
            } else {

                final T toBeShifted = input[index2];

                // Shift all elements 1 step to make place for element at index2
                int i = index2;
                while (i > index1) {
                    input[i] = input[i - 1];
                    i--;
                }
                input[index1] = toBeShifted;

                // Change the scope of both arrays to be compared.
                index1++;
                endIndex1++;
                index2++;
            }
        }
    }
}
