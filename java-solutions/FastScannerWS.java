import java.io.*;
import java.nio.charset.StandardCharsets;

public final class FastScannerWS {
    private final Reader reader;
    private final char[] buffer = new char[1024];
    private int sizeBuffer = 0;
    private int currentPos = 0;
    private int currentLine = 0;
    private boolean modificate = false;

    public FastScannerWS(Reader reader, boolean flag) throws IOException {
        this.reader = reader;
        sizeBuffer = reader.read(buffer);
        currentPos = 0;
        currentLine = 0;
        modificate = flag;
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
        currentLine++;
        if (buffer[currentPos] == '\r') {
            currentPos++;
            if (hasNextLine() && buffer[currentPos] == '\n') {
                currentPos++;
            }
        } else {
            currentPos++;
        }
    }

    public int getCurrentLine() throws IOException {
        return currentLine;
    }

    private boolean isWordOrSpace(char ch) throws IOException {
        boolean isWord = Character.isLetter(ch) || ch == '\'' || Character.getType(ch) == Character.DASH_PUNCTUATION;
        if (modificate) {
            isWord = isWord || ch == '$' || ch == '_' || Character.isDigit(ch);
        }
        return isWord;
    }

    private void skipWhiteSpace() throws IOException {
        while (hasNextLine()) {
            if (!isWordOrSpace(buffer[currentPos])) {
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

    public boolean hasNextWord() throws IOException {
        skipWhiteSpace();
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
        skipWhiteSpace();
        while (hasNextLine() && isWordOrSpace(buffer[currentPos])) {
            sb.append(buffer[currentPos]);
            currentPos++;
        }
        return sb.toString();
    }
}
