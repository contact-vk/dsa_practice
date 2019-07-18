package mathematics;

import org.junit.Test;

public class MatrixMultiplicationTest {

    @Test
    public void testMatrixMultiplication() {
        MatrixMultiplication.execute("Test Case 1:", new int[][]{}, new int[][]{});

        MatrixMultiplication.execute("Test Case 2:", new int[][]{{1, 2, 3}, {4, 5, 6}}, new int[][]{{7, 8}, {9, 10}, {11, 12}});
    }
}
