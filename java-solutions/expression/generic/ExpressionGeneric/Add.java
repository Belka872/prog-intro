package expression.generic.ExpressionGeneric;

public class Add<T> extends Operation<T> {
    public Add(ExpressionUnion<T> left, ExpressionUnion<T> right, GenericOperation<T> mode) {
        super(left, right, mode);
    }

    @Override
    protected T solve(T left, T right) {
        return mode.add(left, right);
    }

    @Override
    protected String getOperator() {
        return "+";
    }

}
