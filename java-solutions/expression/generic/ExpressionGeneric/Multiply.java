package expression.generic.ExpressionGeneric;

public class Multiply<T> extends Operation<T> {
    public Multiply(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode ) {
        super(left, right, mode);
    }

    @Override
    protected T solve(T left, T right) {
        return mode.multiply(left,right);
    }

    @Override
    protected String getOperator() {
        return "*";
    }
}
