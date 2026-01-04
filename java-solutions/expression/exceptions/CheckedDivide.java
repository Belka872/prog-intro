package expression.exceptions;

import expression.ExpressionUnion;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }
    public static int divide(int left, int right) {
        if (right == 0) {
            throw new ZeroException("zero: " + left + "/" + right);
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException("overflow: " + left + "/" + right);
        }
        return left / right;
    }
    @Override
    protected int solve(int left, int right) {
        return divide(left, right);
    }
}
