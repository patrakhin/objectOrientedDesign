package ru;

public class Matrix {
    public static void main(String[] args) {
        int[][] matrix1 = generateScheme(4, 4, 1);
        int[][] matrix2 = generateScheme(6, 6, 2);
        int[][] matrix3 = generateScheme(8, 8, 3);

        printMatrix(matrix1);
        System.out.println();
        printMatrix(matrix2);
        System.out.println();
        printMatrix(matrix3);
    }

    public static int[][] generateScheme(int rows, int cols, int schemeNumber) {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int minDistToEdge = Math.min(Math.min(i, rows - 1 - i), Math.min(j, cols - 1 - j));
                matrix[i][j] = schemeNumber - minDistToEdge;
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

