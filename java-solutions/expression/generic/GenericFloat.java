package expression.generic;

import expression.exceptions.OverflowException;
import expression.exceptions.ZeroException;
import expression.generic.ExpressionGeneric.GenericOperation;

public class GenericFloat implements GenericOperation<Float> {
    @Override
    public Float add(Float left, Float right) {
        return left + right;
    }

    @Override
    public Float divide(Float left, Float right) {
        return left / right;
    }

    @Override
    public Float multiply(Float left, Float right) {
        return left * right;
    }

    @Override
    public Float subtract(Float left, Float right) {
        return left - right;
    }

    @Override
    public Float negate(Float left) {
        return -left;
    }

    @Override
    public Float parseNumber(String s) {
        return Float.parseFloat(s);
    }

    @Override
    public Float count(Float left) {
        return (float) Integer.bitCount(Float.floatToIntBits(left));
    }

    @Override
    public Float min(Float left, Float right) {
        return Float.min(left,right);
    }

    @Override
    public Float max(Float left, Float right) {
        return Float.max(left,right);
    }

    @Override
    public Float convertToInt(int x) {
        return (float) x;
    }

}
