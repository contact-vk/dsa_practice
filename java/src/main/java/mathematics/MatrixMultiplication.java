package mathematics;

import java.util.Arrays;

public class MatrixMultiplication {

    public static void execute(final String message, final int[][] input1, final int[][] input2) {

        System.out.println(message);
        Arrays.stream(multiply(input1, input2)).map(Arrays::toString).forEach(System.out::println);
    }

    private static int[][] multiply(final int[][] input1, final int[][] input2) {

        if (input1.length == 0 || input2.length == 0) {
            return new int[][]{};
        }

        final int[][] result = new int[input1.length][input2[0].length];

        for (int i = 0; i < input1.length; i++) {
            for (int k = 0; k < input2[0].length; k++) {
                for (int j = 0; j < input1[i].length; j++) {
                    result[i][k] += input1[i][j] * input2[j][k];
                }
            }
        }
        return result;
    }
}
