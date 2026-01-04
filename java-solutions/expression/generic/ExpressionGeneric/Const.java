package expression.generic.ExpressionGeneric;

import java.util.Objects;

public class Const<T> implements ExpressionUnion<T> {
    private final T GenericX;
    public Const(T x) {
        this.GenericX = x;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return GenericX;
    }
    @Override
    public String toString() {
        return String.valueOf(GenericX);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()){
            Const<?> templ = (Const<?>) obj;
            return Objects.equals(templ.GenericX, this.GenericX);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(GenericX, this.getClass());
    }

}
