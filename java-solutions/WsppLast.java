import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;


public class WsppLast {
    public static void main(String[] args) {
        Map<String, IntList> positionWord = new LinkedHashMap<>();
        Map<String, Integer> linesWord = new LinkedHashMap<>();
        Map<String, Integer> countWord = new LinkedHashMap<>();
        WordList allWord = new WordList();
        int indexWord = 1;
        try {
            FastScannerWS scannerWS = new FastScannerWS(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), true);
            try {
                while (scannerWS.hasNextWord()) {
                    String word = scannerWS.nextWord().toLowerCase();
                    int countLine = scannerWS.getCurrentLine();
                    positionWord.putIfAbsent(word, new IntList(indexWord));
                    int countWordCurrent = countWord.getOrDefault(word, 0);
                    if (countWordCurrent == 0) {
                        allWord.addWord(word);
                    }
                    countWord.put(word, countWordCurrent + 1);
                    IntList cur = positionWord.get(word);
                    if (linesWord.getOrDefault(word, countLine) == countLine) {
                        cur.replaceLast(indexWord);
                    } else {
                        cur.addInt(indexWord);
                    }
                    linesWord.put(word, countLine);
                    indexWord++;
                }
            } finally {
                scannerWS.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
        try {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            )) {
                for (String word : allWord.getList()) {
                    int[] idxWord = positionWord.get(word).getList();
                    writer.write(word + " " + countWord.get(word) + " ");
                    for (int idx = 0; idx < idxWord.length; idx++) {
                        writer.write(String.valueOf(idxWord[idx]));
                        if (idx != idxWord.length - 1) {
                            writer.write(" ");
                        }
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}