package expression.generic.ExpressionGeneric;

import java.util.Objects;

public abstract class Operation<T> implements ExpressionUnion<T> {
    protected final ExpressionUnion<T> left;
    protected final ExpressionUnion<T> right;
    protected final GenericOperation<T> mode;

    public Operation(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode) {
        this.left = left;
        this.right = right;
        this.mode = mode;
    }

    protected abstract T solve(T left, T right);

    @Override
    public T evaluate(T x, T y, T z) {
        return solve(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract String getOperator();

    @Override
    public String toString() {
        return "(" + left.toString() + " " + getOperator() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            Operation<?> templ = (Operation<?>) obj;
            return (Objects.equals(templ.left, this.left)
                    && Objects.equals(templ.right, this.right)
                    && Objects.equals(templ.getOperator(), this.getOperator()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, this.getClass(), this.getOperator());
    }
}
