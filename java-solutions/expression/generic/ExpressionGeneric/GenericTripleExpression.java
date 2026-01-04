package expression.generic.ExpressionGeneric;

public interface GenericTripleExpression<T> {
    T evaluate(T x, T y, T z);
}
