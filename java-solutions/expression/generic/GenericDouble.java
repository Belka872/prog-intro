package expression.generic;


import expression.generic.ExpressionGeneric.GenericOperation;

public class GenericDouble implements GenericOperation<Double> {
    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double negate(Double left) {
        return -left;
    }

    @Override
    public Double parseNumber(String s) {
        return Double.parseDouble(s);
    }

    @Override
    public Double count(Double left) {
        return (double) Long.bitCount(Double.doubleToLongBits(left));
    }

    @Override
    public Double min(Double left, Double right) {
        return Double.min(left,right);
    }

    @Override
    public Double max(Double left, Double right) {
        return Double.max(left,right);
    }

    @Override
    public Double convertToInt(int x) {
        return (double) x;
    }
}
