import java.io.IOException;
import java.util.Arrays;

public class Reverse {
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

    public static void main(String[] args) {
        try {
            FastScanner sc = new FastScanner(System.in);
            try {
                int[][] allText = new int[BASE][];
                int countLines = 0;
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
                    countLines++;
                }
                for (int i = countLines - 1; i >= 0; i--) {
                    for (int j = allText[i].length - 1; j >= 0; j--) {
                        System.out.print(allText[i][j] + " ");
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
