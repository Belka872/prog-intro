package expression.exceptions;

import expression.Add;
import expression.ExpressionUnion;

public class CheckedAdd extends Add {

    public CheckedAdd(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    @Override
    protected int solve(int left, int right) {
        if (left > 0 && right > 0 && left > Integer.MAX_VALUE - right) {
            throw new OverflowException("overflow: " + left + "+" + right);
        }
        if (left < 0 && right < 0 && left < Integer.MIN_VALUE - right) {
            throw new OverflowException("overflow: " + left + "+" + right);
        }
        return left + right;
    }

}
