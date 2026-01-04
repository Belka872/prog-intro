package expression.exceptions;

import expression.ExpressionUnion;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }
    public static int multiply(int left, int right) {
        if (left > 0 && right > 0 && left > Integer.MAX_VALUE / right) {
            throw new OverflowException("overflow: " + left + "*" + right);
        }
        if (left < 0 && right < 0 && left < Integer.MAX_VALUE / right) {
            throw new OverflowException("overflow: " + left + "*" + right);
        }
        if (left > 0 && right < 0 && right < Integer.MIN_VALUE / left) {
            throw new OverflowException("overflow: " + left + "*" + right);
        }
        if (left < 0 && right > 0 && left < Integer.MIN_VALUE / right) {
            throw new OverflowException("overflow: " + left + "*" + right);
        }
        return left * right;
    }
    @Override
    protected int solve(int left, int right) {
        return multiply(left,right);
    }
}
