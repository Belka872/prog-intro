package expression.parser;

public class BaseParser {
    protected CharSource source;
    protected char ch;
    public static final char END = (char) -1;


    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(char c) {
        return ch == c;
    }

    protected char take() {
        char res = ch;
        ch = source.hasNext() ? source.next() : END;
        return res;
    }
    protected boolean take(String s) {
        String source_s = source.peek(s.length()-1);
        if (source_s.equals(s.substring(1))) {
            for (int i = 0; i < s.length(); i++) {
                take();
            }
            return true;
        }
        return false;
    }
    protected boolean take(char c) {
        if (test(c)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean checkEOF() {
        return ch == END;
    }

    protected void expect(char c) {
        if (!take(c)) {
            throw source.error("Expected: " + c + " , but found: " + ch);
        }
    }

    protected void expect(String s) {
        for (char c : s.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(char start, char end) {
        return start <= ch && ch <= end;
    }
}
