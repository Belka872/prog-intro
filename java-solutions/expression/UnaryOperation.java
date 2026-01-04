package expression;

import java.util.Objects;

public abstract class UnaryOperation implements ExpressionUnion {
    protected final ExpressionUnion currentExpression;

    public UnaryOperation(ExpressionUnion currentExpression) {
        this.currentExpression = currentExpression;
    }

    protected abstract int solve(int current);

    @Override
    public int evaluate(int x) {
        return solve(currentExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return solve(currentExpression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "-(" + currentExpression.toString() + ")";
    }

    protected abstract String getOperator();

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            UnaryOperation templ = (UnaryOperation) obj;
            return (Objects.equals(templ.currentExpression, this.currentExpression)
                    && getOperator().equals(templ.getOperator()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentExpression, this.getClass(), this.getOperator());
    }
}
