package solution;

public class task4 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1},
        };

        printMatrix(matrix);
        int[][] matrix2 = createNewMatrix(matrix, 2);
        printMatrix(matrix2);
        int[][] matrix3 = createNewMatrix(matrix2, 3);
        printMatrix(matrix3);


    }

    static int[][] createNewMatrix(int[][] matrix, int fillValue) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] finalMatrix = new int[rows + 2][columns + 2];

        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < columns + 2; j++) {
                if (i == 0 || i == rows + 1 || j == 0 || j == columns + 1) {
                    finalMatrix[i][j] = fillValue;
                } else {
                    finalMatrix[i][j] = matrix[i - 1][j - 1];
                }
            }
        }
        return finalMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}