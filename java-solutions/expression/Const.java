package expression;

import java.util.Map;
import java.util.Objects;

public class Const implements ExpressionUnion {
    private final Number x;

    public Const(int x) {
        this.x = x;
    }

    public Const(double x) {
        this.x = x;
    }

    @Override
    public int evaluate(int x) {
        return this.x.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.x.intValue();
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return this.x.doubleValue();
    }

    @Override
    public String toString() {
        return String.valueOf(x);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            Const templ = (Const) obj;
            return Objects.equals(templ.x, this.x);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, this.getClass());
    }
}
