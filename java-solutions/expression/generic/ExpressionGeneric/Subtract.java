package expression.generic.ExpressionGeneric;

public class Subtract<T> extends Operation<T> {
    public Subtract(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode) {
        super(left, right, mode);
    }

    @Override
    protected T solve(T left, T right) {
        return mode.subtract(left,right);
    }

    @Override
    protected String getOperator() {
        return "-";
    }
}
