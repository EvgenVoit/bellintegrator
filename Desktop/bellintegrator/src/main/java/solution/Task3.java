package solution;

public class Task3 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {5, 7, 9, 2, 1},
                {0, 9, 1, 8, 7},
                {6, 3, 6, 6, 6},
                {99, 100, -2, 3, 1}
        };
        int minDiagonalElement = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            int j = matrix.length - 1 - i;
            if (i != j && matrix[i][j] < minDiagonalElement) {
                minDiagonalElement = matrix[i][j];
            }
        }

        System.out.println("Минимальный элемент на выделенной диагонали - " + minDiagonalElement);
    }
}