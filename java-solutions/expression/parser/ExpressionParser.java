package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    public TripleExpression parse(String string) {
        return new Parse(new StringSource(string)).parse();
    }

    // :NOTE: maybe static
    private class Parse extends BaseParser {
        public Parse(CharSource source) {
            super(source);
        }

        private void skipWS() {
            while (Character.isWhitespace(ch)) {
                take();
            }
        }
        public TripleExpression parse() {
            TripleExpression result = parseOr();
            if (!checkEOF()) {
                throw new IllegalArgumentException("Expected EOF, but found:" + take());
            }
            return result;
        }

        private ExpressionUnion parseOr() {
            skipWS();
            ExpressionUnion result = parseXor();
            skipWS();
            while (take('|')) {
                ExpressionUnion right = parseXor();
                result = new Or(result, right);
                skipWS();
            }
            return result;
        }

        private ExpressionUnion parseXor() {
            skipWS();
            ExpressionUnion result = parseAnd();
            skipWS();
            while (take('^')) {
                ExpressionUnion right = parseAnd();
                result = new Xor(result, right);
                skipWS();
            }
            return result;
        }

        private ExpressionUnion parseAnd() {
            skipWS();
            ExpressionUnion result = parseFirstLayer();
            skipWS();
            while (take('&')) {
                ExpressionUnion right = parseFirstLayer();
                result = new And(result, right);
                skipWS();
            }
            return result;
        }

        private ExpressionUnion parseFirstLayer() {
            skipWS();
            ExpressionUnion result = parseSecondLayer();
            skipWS();
            while (test('+') || test('-')) {
                char operator = take();
                ExpressionUnion right = parseSecondLayer();
                if (operator == '+') {
                    result = new Add(result, right);
                } else {
                    result = new Subtract(result, right);
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
                    result = new Multiply(result, right);
                } else {
                    result = new Divide(result, right);
                }
                skipWS();
            }
            return result;
        }

        private Const parseNumber(boolean is_minus) {
            StringBuilder sb = new StringBuilder();
            if(is_minus) {
                sb.append("-");
            }
            while (between('0', '9')) {
                sb.append(take());
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Expected number, but found:" + take());
            }
        }

        private ExpressionUnion parseThirdLayer() {
            skipWS();
            if (take('(')) {
                ExpressionUnion result = parseOr();
                expect(')');
                return result;
            } else if (test('-')) {
                take();
                if (between('0', '9')) {
                    return parseNumber(true);
                } else {
                    return new UnaryMinus(parseThirdLayer());
                }
            } else if (between('0', '9')) {
                return parseNumber(false);
            } else if (take('x')) {
                return new Variable("x");
            } else if (take('y')) {
                return new Variable("y");
            } else if (take('z')) {
                return new Variable("z");
            }
            throw new IllegalArgumentException("Expected correct symbol, but found:" + take());
        }
    }

}
