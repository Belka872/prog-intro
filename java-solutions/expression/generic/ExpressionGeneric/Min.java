package expression.generic.ExpressionGeneric;

public class Min<T> extends Operation<T> {
    public Min(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode) {
        super(left, right, mode);
    }

    @Override
    protected T solve(T left, T right) {
        return mode.min(left, right);
    }

    @Override
    protected String getOperator() {
        return "min";
    }

}
