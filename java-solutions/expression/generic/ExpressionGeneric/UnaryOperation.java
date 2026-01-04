package expression.generic.ExpressionGeneric;

import java.util.Objects;

public abstract class UnaryOperation<T> implements ExpressionUnion<T> {
    protected final ExpressionUnion<T> currentExpression;
    protected final GenericOperation<T> mode;

    public UnaryOperation(ExpressionUnion<T> currentExpression, GenericOperation<T> mode) {
        this.currentExpression = currentExpression;
        this.mode = mode;
    }

    protected abstract T solve(T current);

    public T evaluate(T x, T y, T z) {
        return solve(currentExpression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return getOperator() + "(" + currentExpression.toString() + ")";
    }

    protected abstract String getOperator();

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            UnaryOperation<?> templ = (UnaryOperation<?>) obj;
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
