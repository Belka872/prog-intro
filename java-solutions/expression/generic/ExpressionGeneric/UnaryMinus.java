package expression.generic.ExpressionGeneric;

public class UnaryMinus<T> extends UnaryOperation<T> {

    public UnaryMinus(ExpressionUnion<T> expresion, GenericOperation<T> mode) {
        super(expresion, mode);
    }

    public T solve(T x) {
        return mode.negate(x);
    }

    @Override
    protected String getOperator() {
        return "-";
    }

}
