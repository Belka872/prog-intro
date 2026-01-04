package expression.exceptions;

import expression.ExpressionUnion;
import expression.UnaryMinus;
import expression.UnaryOperation;

public class CheckedNegate extends UnaryMinus {

    public CheckedNegate(ExpressionUnion expresion) {
        super(expresion);
    }

    @Override
    public int solve(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("overflow: -" + x );
        }
        return -x;
    }

}
