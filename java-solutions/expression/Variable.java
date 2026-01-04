package expression;

import java.util.Map;
import java.util.Objects;

public class Variable implements ExpressionUnion {
    private final String x;

    public Variable(String x) {
        this.x = x;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (Objects.equals(this.x, "x")) return x;
        else if (Objects.equals(this.x, "y")) return y;
        else return z;
    }

    @Override
    public double evaluateD(double x, double y, double z) {
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
        if (obj != null && this.getClass() == obj.getClass()){
            Variable templ = (Variable) obj;
            return Objects.equals(templ.x, this.x);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, this.getClass());
    }
}
