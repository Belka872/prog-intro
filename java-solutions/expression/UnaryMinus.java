package expression;

import java.util.Objects;

public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(ExpressionUnion expresion) {
        super(expresion);
    }

    @Override
    public int solve(int x) {
        return -x;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return -x;
    }
}
