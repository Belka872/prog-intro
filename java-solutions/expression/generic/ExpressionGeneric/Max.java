package expression.generic.ExpressionGeneric;

public class Max<T> extends Operation<T> {
    public Max(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode) {
        super(left, right, mode);
    }

    @Override
    protected T solve(T left, T right) {
        return mode.max(left, right);
    }

    @Override
    protected String getOperator() {
        return "max";
    }

}
