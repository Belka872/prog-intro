package expression.exceptions;

import expression.ExpressionUnion;
import expression.Operation;

public class CheckedLcm extends Operation {

    public CheckedLcm(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    @Override
    protected int solve(int left, int right) {
        if(left==0 || right==0) {
            return 0;
        }
        int GcdLeftRight = CheckedGcd.gcd(left,right);
        int DivideLeftGcd = CheckedDivide.divide(left,GcdLeftRight);
        return CheckedMultiply.multiply(DivideLeftGcd, right);
    }

    @Override
    protected double solveD(double left, double right) {
        return 0;
    }

    @Override
    protected String getOperator() {
        return "lcm";
    }

}
