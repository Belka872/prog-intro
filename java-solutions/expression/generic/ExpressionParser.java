package expression.generic;

import expression.generic.ExpressionGeneric.*;
import expression.exceptions.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;


public class ExpressionParser<T> {
    private final GenericOperation<T> mode;

    public ExpressionParser(GenericOperation<T> mode) {
        this.mode = mode;
    }

    public ExpressionUnion<T> parse(String string) {
        return new Parse(new StringSource(string)).parse();
    }

    private class Parse extends BaseParser {
        public Parse(CharSource source) {
            super(source);
        }

        private void skipWS() {
            while (Character.isWhitespace(ch)) {
                take();
            }
        }

        public ExpressionUnion<T> parse() {
            ExpressionUnion<T> result = parseMinMax();
            if (!checkEOF()) {
                throw new ParseException("Expected EOF, but found:" + take() + " Position:" + (source.getPos() - 1));
            }
            return result;
        }

        private ExpressionUnion<T> parseMinMax() {
            skipWS();
            ExpressionUnion<T> result = parseFirstLayer();
            skipWS();
            while (true) {
                if (take("max")) {
                    ExpressionUnion<T> right = parseFirstLayer();
                    result = new Max<>(result, right, mode);
                } else if (take("min")) {
                    ExpressionUnion<T> right = parseFirstLayer();
                    result = new Min<>(result, right, mode);
                } else {
                    return result;
                }
                skipWS();
            }
        }

        private ExpressionUnion<T> parseFirstLayer() {
            skipWS();
            ExpressionUnion<T> result = parseSecondLayer();
            skipWS();
            while (test('+') || test('-')) {
                char operator = take();
                ExpressionUnion<T> right = parseSecondLayer();
                if (operator == '+') {
                    result = new Add<>(result, right, mode);
                } else {
                    result = new Subtract<>(result, right, mode);
                }
                skipWS();
            }
            return result;
        }

        private ExpressionUnion<T> parseSecondLayer() {
            skipWS();
            ExpressionUnion<T> result = parseThirdLayer();
            skipWS();
            while (test('*') || test('/')) {
                char operator = take();
                ExpressionUnion<T> right = parseThirdLayer();
                if (operator == '*') {
                    result = new Multiply<>(result, right, mode);
                } else {
                    result = new Divide<>(result, right, mode);
                }
                skipWS();
            }
            return result;
        }

        private Const<T> parseNumber(boolean is_minus) {
            StringBuilder sb = new StringBuilder();
            if (is_minus) {
                sb.append("-");
            }
            while (between('0', '9')) {
                sb.append(take());
            }
            try {
                T result = mode.parseNumber(sb.toString());
                return new Const<>(result);
            } catch (NumberFormatException e) {
                throw new ConstException("Expected correct number, but found:" + sb.toString() + " Position:" + (source.getPos() - 1));
            }
        }

        private ExpressionUnion<T> parseThirdLayer() {
            skipWS();
            if (take('(')) {
                ExpressionUnion<T> result = parseMinMax();
                if (!take(')')) {
                    throw new ParseException("Expected ')', but found:" + take());
                }
                return result;
            } else if (test('-')) {
                take();
                if (between('0', '9')) {
                    return parseNumber(true);
                } else {
                    return new UnaryMinus<>(parseThirdLayer(), mode);
                }
            } else if (take("count")) {
                return new UnaryCount<>(parseThirdLayer(), mode);
            } else if (between('0', '9')) {
                return parseNumber(false);
            } else if (between('a', 'z') || between('A', 'Z')) {
                if (take('x')) {
                    return new Variable<>("x");
                } else if (take('y')) {
                    return new Variable<>("y");
                } else if (take('z')) {
                    return new Variable<>("z");
                }
                throw new VariableException("Expected correct Varuable, but found:" + take() + " Position:" + (source.getPos() - 1));
            }
            throw new ParseException("Expected correct symbol for parse, but found:" + take() + " Position:" + (source.getPos() - 1));
        }
    }

}
