package expression.parser;

import expression.exceptions.ParseException;

public interface CharSource {
    boolean hasNext();
    char next();
    String peek(int len);
    ParseException error(String message);
    int getPos();
}
