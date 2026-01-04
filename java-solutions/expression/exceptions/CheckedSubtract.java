package expression.exceptions;

import expression.ExpressionUnion;
import expression.Operation;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    @Override
    protected int solve(int left, int right) {
        if (left >= 0 && right < 0 && left > Integer.MAX_VALUE + right) {
            throw new OverflowException("overflow: " + left + "-" + right);
        }
        if (left <= 0 && right > 0 && left < Integer.MIN_VALUE + right) {
            throw new OverflowException("overflow: " + left + "-" + right);
        }
        return left - right;
    }
}
