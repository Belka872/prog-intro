import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class ReverseAvg {
    public static int BASE = 16;

    public static int[][] doubleArray2D(int[][] array) {
        return Arrays.copyOf(array, array.length * 2);
    }

    public static int[] doubleArray1D(int[] array) {
        return Arrays.copyOf(array, array.length * 2);
    }

    public static int[] cropArray(int[] array, int length) {
        return Arrays.copyOf(array, length);
    }

    public static long getAvg(int[][] allText, long[] sumColumns, long[] sumRow, long[] countColumns, long[] countRow,
                              int row, int column) {
        return ((sumRow[row] + sumColumns[column] - (long) allText[row][column])
                / (countRow[row] + countColumns[column] - 1));
    }

    public static void main(String[] args) {
        try {
            FastScanner sc = new FastScanner(System.in);
            try {
                int[][] allText = new int[BASE][];
                int countLines = 0;
                int maxLen = 0;
                while (sc.hasNextLine()) {
                    if (countLines == allText.length) {
                        allText = doubleArray2D(allText);
                    }
                    String newLine = sc.nextLine();
                    int countNumber = 0;
                    try {
                        FastScanner scanNumbers = new FastScanner(newLine);
                        try {
                            allText[countLines] = new int[BASE];
                            while (scanNumbers.hasNextInt()) {
                                if (countNumber == allText[countLines].length) {
                                    allText[countLines] = doubleArray1D(allText[countLines]);
                                }
                                allText[countLines][countNumber] = scanNumbers.nextInt();
                                countNumber++;
                            }
                        } finally {
                            scanNumbers.close();
                        }
                    } catch (IOException e) {
                        System.err.println("IO error: " + e.getMessage());
                    }
                    allText[countLines] = cropArray(allText[countLines], countNumber);
                    maxLen = Math.max(maxLen, countNumber);
                    countLines++;
                }
                long[] sumRow = new long[countLines];
                long[] sumColumns = new long[maxLen];
                long[] countRow = new long[countLines];
                long[] countColumns = new long[maxLen];
                for (int i = 0; i < countLines; i++) {
                    for (int j = 0; j < allText[i].length; j++) {
                        sumRow[i] += allText[i][j];
                        countRow[i]++;
                        sumColumns[j] += allText[i][j];
                        countColumns[j]++;
                    }
                }
                for (int i = 0; i < countLines; i++) {
                    for (int j = 0; j < allText[i].length; j++) {
                        System.out.print(getAvg(allText, sumColumns, sumRow, countColumns, countRow, i, j) + " ");
                    }
                    System.out.println();
                }
            } finally {
                sc.close();
            }
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}
