package expression.exceptions;

import expression.Add;
import expression.ExpressionUnion;
import expression.Operation;

public class CheckedGcd extends Operation {

    public CheckedGcd(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    public static int gcd(int left, int right) {
        while (right != 0) {
            left %= right;
            int temp = right;
            right = left;
            left = temp;
        }
        if (left == Integer.MIN_VALUE) throw new OverflowException("overflow: " + left + " gcd " + right);
        if (left < 0) return -left;
        return left;
    }

    @Override
    protected int solve(int left, int right) {
        return gcd(left, right);
    }

    @Override
    protected double solveD(double left, double right) {
        return 0;
    }

    @Override
    protected String getOperator() {
        return "gcd";
    }

}
