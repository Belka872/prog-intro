package expression;

public class Divide extends Operation {
    public Divide(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    @Override
    protected int solve(int left, int right) {
        return left / right;
    }

    @Override
    protected double solveD(double left, double right) {
        return left / right;
    }

    @Override
    protected String getOperator() {
        return "/";
    }
}
