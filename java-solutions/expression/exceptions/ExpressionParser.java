package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;


public class ExpressionParser implements TripleParser {
    public TripleExpression parse(String string) {
        return new Parse(new StringSource(string)).parse();
    }

    private static class Parse extends BaseParser {
        public Parse(CharSource source) {
            super(source);
        }

        private void skipWS() {
            while (Character.isWhitespace(ch)) {
                take();
            }
        }

        public TripleExpression parse() {
            TripleExpression result = parseGcdLcm();
            if (!checkEOF()) {
                throw new ParseException("Expected EOF, but found:" + ch + " Position:" + (source.getPos() - 1));
            }
            return result;
        }

        private ExpressionUnion parseGcdLcm() {
            skipWS();
            ExpressionUnion result = parseFirstLayer();
            skipWS();
            while (true) {
                if (take("gcd")) {
                    if (Character.isLetterOrDigit(ch)) {
                        throw new ParseException("Expected correct symbol for gcd, but found:" + ch + " Position:" + (source.getPos() - 1));
                    }
                    result = new CheckedGcd(result, parseFirstLayer());
                } else if (take("lcm")) {
                    if (Character.isLetterOrDigit(ch)) {
                        throw new ParseException("Expected correct symbol for lcm, but found:" + ch + " Position:" + (source.getPos() - 1));
                    }
                    result = new CheckedLcm(result, parseFirstLayer());
                } else {
                    return result;
                }
                skipWS();
            }
        }

        private ExpressionUnion parseFirstLayer() {
            skipWS();
            ExpressionUnion result = parseSecondLayer();
            skipWS();
            while (test('+') || test('-')) {
                char operator = take();
                ExpressionUnion right = parseSecondLayer();
                if (operator == '+') {
                    result = new CheckedAdd(result, right);
                } else {
                    result = new CheckedSubtract(result, right);
                }
                skipWS();
            }
            return result;
        }

        private ExpressionUnion parseSecondLayer() {
            skipWS();
            ExpressionUnion result = parseThirdLayer();
            skipWS();
            while (test('*') || test('/')) {
                char operator = take();
                ExpressionUnion right = parseThirdLayer();
                if (operator == '*') {
                    result = new CheckedMultiply(result, right);
                } else {
                    result = new CheckedDivide(result, right);
                }
                skipWS();
            }
            return result;
        }

        private Const parseNumber(boolean is_minus) {
            StringBuilder sb = new StringBuilder();
            if (is_minus) {
                sb.append("-");
            }
            while (between('0', '9')) {
                sb.append(take());
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new ConstException("Expected correct number, but found:" + sb.toString() + " Position:" + (source.getPos() - 1));
            }
        }

        private ExpressionUnion parseThirdLayer() {
            skipWS();
            if (take('(')) {
                ExpressionUnion result = parseGcdLcm();
                if (!take(')')) {
                    throw new ParseException("Expected ')', but found:" + ch + " Position:" + (source.getPos() - 1));
                }
                return result;
            } else if (test('-')) {
                take();
                if (between('0', '9')) {
                    return parseNumber(true);
                } else {
                    return new CheckedNegate(parseThirdLayer());
                }
            } else if (between('0', '9')) {
                return parseNumber(false);
            } else if (between('a', 'z') || between('A', 'Z')) {
                if (take('x')) {
                    return new Variable("x");
                } else if (take('y')) {
                    return new Variable("y");
                } else if (take('z')) {
                    return new Variable("z");
                }
                throw new VariableException("Expected correct Variable, but found:" + ch + " Position:" + (source.getPos() - 1));
            }
            throw new ParseException("Expected correct symbol for parse, but found:" + ch + " Position:" + (source.getPos() - 1));
        }
    }

}
