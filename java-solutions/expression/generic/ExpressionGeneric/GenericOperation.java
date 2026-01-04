package expression.generic.ExpressionGeneric;

public interface GenericOperation<T>  {
    T add(T left, T right);
     T divide(T left, T right);
     T multiply(T left, T right);
     T subtract(T left, T right);
     T negate(T left);
     T parseNumber(String s);
     T count(T left);
     T min(T left, T right);
     T max(T left, T right);
     T convertToInt(int left);
}
