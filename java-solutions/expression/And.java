package expression;

public class And extends Operation {
    public And(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    @Override
    protected int solve(int left, int right) {
        return left & right;
    }

    @Override
    protected double solveD(double left, double right) {
        return 0;
    }

    @Override
    protected String getOperator() {
        return "&";
    }

}
