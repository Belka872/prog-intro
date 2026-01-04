package expression.generic.ExpressionGeneric;

public class Divide<T> extends Operation<T> {
    public Divide(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode ) {
        super(left, right, mode);
    }
    @Override
    protected T solve(T left, T right) {
        return mode.divide(left,right);
    }

    @Override
    protected String getOperator() {
        return "/";
    }
}
