import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Wspp {
    public static void main(String[] args) {
        Map<String, IntList> countWord = new LinkedHashMap<>();
        int indexWord = 1;
        try {
            FastScannerWS scannerWS = new FastScannerWS(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), false);
            try {
                while (scannerWS.hasNextWord()) {
                    String word = scannerWS.nextWord().toLowerCase();
                    IntList cur = countWord.getOrDefault(word, new IntList());
                    cur.addInt(indexWord);
                    indexWord++;
                    countWord.put(word, cur);
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
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            );
            try {
                for (String word : countWord.keySet()) {
                    int[] idxWord = countWord.get(word).getList();
                    writer.write(word + " " + idxWord.length + " ");
                    for (int idx = 0; idx < idxWord.length; idx++) {
                        writer.write(String.valueOf(idxWord[idx]));
                        if (idx != idxWord.length - 1) {
                            writer.write(" ");
                        }
                    }
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