package expression;

public class Xor extends Operation {
    public Xor(ExpressionUnion left, ExpressionUnion right) {
        super(left, right);
    }

    @Override
    protected int solve(int left, int right) {
        return left ^ right;
    }

    @Override
    protected double solveD(double left, double right) {
        return 0;
    }

    @Override
    protected String getOperator() {
        return "^";
    }

}
