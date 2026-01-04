package expression.generic.ExpressionGeneric;

import java.util.Objects;

public class Variable<T> implements ExpressionUnion<T> {
    private final String x;

    public Variable(String x) {
        this.x = x;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if (Objects.equals(this.x, "x")) return x;
        else if (Objects.equals(this.x, "y")) return y;
        else return z;
    }

    @Override
    public String toString() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            Variable<?> templ = (Variable<?>) obj;
            return Objects.equals(templ.x, this.x);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, this.getClass());
    }

}
