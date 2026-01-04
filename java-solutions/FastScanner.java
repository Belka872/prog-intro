import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.*;

public final class FastScanner {
    private final Reader reader;
    private final char[] buffer = new char[1024];
    private int sizeBuffer = 0;
    private int currentPos = 0;

    public FastScanner(String s) throws IOException {
        this.reader = new StringReader(s);
        sizeBuffer = reader.read(buffer);
        currentPos = 0;
    }

    public FastScanner(InputStream in) throws IOException {
        this.reader = (new InputStreamReader(in, StandardCharsets.UTF_8));
        sizeBuffer = reader.read(buffer);
        currentPos = 0;
    }

    public void close() throws IOException {
        reader.close();
    }

    public boolean hasNextLine() throws IOException {
        if (currentPos < sizeBuffer) {
            return true;
        }
        sizeBuffer = reader.read(buffer);
        currentPos = 0;
        return sizeBuffer != -1;
    }

    private void skipSeparator() throws IOException {
        if (currentPos >= sizeBuffer) return;
        if (buffer[currentPos] == '\r') {
            currentPos++;
            if (hasNextLine() && buffer[currentPos] == '\n') {
                currentPos++;
            }
        } else if (buffer[currentPos] == '\n') {
            currentPos++;
        }
    }

    private boolean isWordOrSpace(char ch) {
        return Character.isLetter(ch) || ch == '\'' || Character.getType(ch) == Character.DASH_PUNCTUATION;
    }

    private boolean isDigitOrSpace(char ch) {
        return Character.isDigit(ch) || ch == '-' || ch == '+';
    }

    private void skipWhiteSpace(boolean digitOrWord) throws IOException {
        while (hasNextLine()) {
            if ((!isWordOrSpace(buffer[currentPos]) && !digitOrWord) || (!isDigitOrSpace(buffer[currentPos]) && digitOrWord)) {
                if (buffer[currentPos] == '\n' || buffer[currentPos] == '\r') {
                    skipSeparator();
                } else {
                    currentPos++;
                }
            } else {
                break;
            }
        }
    }

    public String nextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (hasNextLine()) {
            int start = currentPos;
            while (currentPos < sizeBuffer && (buffer[currentPos] != '\n' && buffer[currentPos] != '\r')) {
                currentPos++;
            }
            sb.append(buffer, start, currentPos - start);
            if (currentPos < sizeBuffer) {

                skipSeparator();
                break;
            }
        }
        return sb.toString();
    }

    public boolean hasNextInt() throws IOException {
        skipWhiteSpace(true);
        if (currentPos >= sizeBuffer) {
            return false;
        }
        int tempPos = currentPos;
        StringBuilder sb = new StringBuilder();
        if (buffer[tempPos] == '-' || buffer[tempPos] == '+') {
            sb.append(buffer[tempPos]);
            tempPos++;
        }
        while (tempPos < sizeBuffer && Character.isDigit(buffer[tempPos])) {
            sb.append(buffer[tempPos]);
            tempPos++;
        }
        return !sb.isEmpty();
    }

    public int nextInt() throws IOException {
        StringBuilder sb = new StringBuilder();
        skipWhiteSpace(true);
        if (buffer[currentPos] == '-' || buffer[currentPos] == '+') {
            sb.append(buffer[currentPos]);
            currentPos++;
        }
        while (hasNextLine() && Character.isDigit(buffer[currentPos])) {
            sb.append(buffer[currentPos]);
            currentPos++;
        }
        return Integer.parseInt(sb.toString());
    }


    public boolean hasNextWord() throws IOException {
        skipWhiteSpace(false);
        if (currentPos >= sizeBuffer) {
            return false;
        }
        int tempPos = currentPos;
        StringBuilder sb = new StringBuilder();
        while (tempPos < sizeBuffer && isWordOrSpace(buffer[tempPos])) {
            sb.append(buffer[tempPos]);
            tempPos++;
        }
        return !sb.isEmpty();
    }

    public String nextWord() throws IOException {
        StringBuilder sb = new StringBuilder();
        skipWhiteSpace(false);
        while (hasNextLine() && isWordOrSpace(buffer[currentPos])) {
            sb.append(buffer[currentPos]);
            currentPos++;
        }
        return sb.toString();
    }
}
