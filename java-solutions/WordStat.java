import java.io.*;
import java.util.Arrays;
import java.util.Objects;

class WordStatistic {
    private String[] uniqueWords;
    private int[] counts;
    private int size;

    public WordStatistic(int baseSize) {
        uniqueWords = new String[baseSize];
        counts = new int[baseSize];
        size = 0;
    }

    public void addWord(String newWord) {
        boolean isNewWord = true;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(uniqueWords[i], newWord)) {
                counts[i]++;
                isNewWord = false;
                break;
            }
        }
        if (isNewWord) {
            if (size == uniqueWords.length) {
                uniqueWords = Arrays.copyOf(uniqueWords, uniqueWords.length * 2);
                counts = Arrays.copyOf(counts, counts.length * 2);
            }
            uniqueWords[size] = newWord;
            counts[size] = 1;
            size++;
        }
    }

    public String[] getUniqueWords() {
        return uniqueWords;
    }

    public int[] getCounts() {
        return counts;
    }

    public int getSize() {
        return size;
    }
}

public class WordStat {
    public static int BASE = 8;

    public static void main(String[] args) {
        WordStatistic wordCount = new WordStatistic(BASE);
        try {
            FastScanner sc = new FastScanner(new FileInputStream(args[0]));
            try {
                while (sc.hasNextWord()) {
                    String word = sc.nextWord().toLowerCase();
                    wordCount.addWord(word);
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
                String[] uniqueWord = wordCount.getUniqueWords();
                int[] countUniqueWord = wordCount.getCounts();
                int size = wordCount.getSize();
                for (int i = 0; i < size; i++) {
                    writer.write(uniqueWord[i] + " " + countUniqueWord[i]);
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
