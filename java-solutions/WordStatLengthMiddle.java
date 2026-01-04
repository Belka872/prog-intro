import java.io.*;
import java.util.*;

public class WordStatLengthMiddle {
    public static int BASE = 8;

    public static void main(String[] args) {
        Map<String, Integer> countWordMap = new HashMap<>();
        String[] uniqueWord = new String[BASE];
        int lenUniqueWord = 0;
        try {
            FastScanner sc = new FastScanner(new FileInputStream(args[0]));
            try {
                while (sc.hasNextWord()) {
                    String word = sc.nextWord().toLowerCase();
                    if (word.length() >= 7) {
                        String newWord = word.substring(3, word.length() - 3);
                        if (countWordMap.get(newWord) == null) {
                            if (lenUniqueWord == uniqueWord.length) {
                                uniqueWord = Arrays.copyOf(uniqueWord, lenUniqueWord * 2);
                            }
                            uniqueWord[lenUniqueWord] = newWord;
                            lenUniqueWord++;
                        }
                        countWordMap.put(newWord, countWordMap.getOrDefault(newWord, 0) + 1);
                    }
                }
            } finally {
                sc.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "UTF8")
            );
            try {
                uniqueWord = Arrays.copyOf(uniqueWord, lenUniqueWord);
                Arrays.sort(uniqueWord, Comparator.comparingInt(String::length));
                for (int i = 0; i < lenUniqueWord; i++) {
                    writer.write(uniqueWord[i] + " " + countWordMap.get(uniqueWord[i]));
                    writer.newLine();
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}
