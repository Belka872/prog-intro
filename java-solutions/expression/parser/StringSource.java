package expression.parser;

import expression.exceptions.ParseException;

public class StringSource implements CharSource {
    private final String string;
    private int pos;

    public StringSource(String string) {
        this.string = string;
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public boolean hasNext() {
        return pos < string.length();
    }

    @Override
    public char next() {
        return string.charAt(pos++);
    }

    @Override
    public String peek(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < Math.min(len + pos, string.length()); i++) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public ParseException error(String message) {
        return new ParseException(pos + ":" + message);
    }
}
