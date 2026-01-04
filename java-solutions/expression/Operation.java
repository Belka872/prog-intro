package expression;

import expression.exceptions.EvaluateException;

import java.util.Map;
import java.util.Objects;

public abstract class Operation implements ExpressionUnion {
    protected final ExpressionUnion left;
    protected final ExpressionUnion right;

    public Operation(ExpressionUnion left, ExpressionUnion right) {
        this.left = left;
        this.right = right;
    }

    protected abstract int solve(int left, int right);
    protected abstract double solveD(double left, double right);


    @Override
    public int evaluate(int x) {
        return solve(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return solve(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return solveD(left.evaluateD(x,y,z), right.evaluateD(x,y,z));
    }

    protected abstract String getOperator();

    @Override
    public String toString() {
        return "(" + left.toString() + " " + getOperator() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()){
            Operation templ = (Operation) obj;
            return (Objects.equals(templ.left, this.left)
                    && Objects.equals(templ.right, this.right)
                    && Objects.equals(templ.getOperator(), this.getOperator()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, this.getClass(),this.getOperator());
    }
}
