package algorithms;

import algoritms.SelectionSort;
import org.junit.Assert;
import org.junit.Test;

public class SelectionSortTest {

    @Test
    public void testSortingWithIntegerArray() {

        // Random inputs
        final Integer[] testCase1 = new Integer[]{
                4, 1, 5, 8, 10, 3, 2, 6, 9, 34
        };
        SelectionSort.sort(testCase1);
        Assert.assertArrayEquals(testCase1, new Integer[]{1, 2, 3, 4, 5, 6, 8, 9, 10, 34});

        // Decreasing order sorted inputs
        final Integer[] testCase2 = new Integer[]{
                34, 25, 20, 17, 14, 11, 7, 6, 4, 2, 1
        };
        SelectionSort.sort(testCase2);
        Assert.assertArrayEquals(testCase2, new Integer[]{1, 2, 4, 6, 7, 11, 14, 17, 20, 25, 34});

        // With duplicates
        final Integer[] testCase3 = new Integer[]{
                34, 25, 20, 17, 17, 14, 7, 14, 11, 7, 6, 4, 2, 1
        };
        SelectionSort.sort(testCase3);
        Assert.assertArrayEquals(testCase3, new Integer[]{1, 2, 4, 6, 7, 7, 11, 14, 14, 17, 17, 20, 25, 34});

        // With already sorted
        final Integer[] testCase4 = new Integer[]{
                1, 2, 4, 6, 7, 11, 14, 17, 20, 25, 34
        };
        SelectionSort.sort(testCase4);
        Assert.assertArrayEquals(testCase4, new Integer[]{1, 2, 4, 6, 7, 11, 14, 17, 20, 25, 34});
    }
}
