package expression.generic.ExpressionGeneric;

public class UnaryCount<T> extends UnaryOperation<T> {

    public UnaryCount(ExpressionUnion<T> expresion, GenericOperation<T> mode) {
        super(expresion, mode);
    }

    public T solve(T x) {
        return mode.count(x);
    }

    @Override
    protected String getOperator() {
        return "count";
    }

}
